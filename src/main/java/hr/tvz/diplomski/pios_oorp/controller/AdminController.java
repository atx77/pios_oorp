package hr.tvz.diplomski.pios_oorp.controller;

import hr.tvz.diplomski.pios_oorp.constant.PagesConstants;
import hr.tvz.diplomski.pios_oorp.dto.AlertMessage;
import hr.tvz.diplomski.pios_oorp.enumeration.AlertType;
import hr.tvz.diplomski.pios_oorp.form.AddOrEditProductForm;
import hr.tvz.diplomski.pios_oorp.form.admin.AddCategoryAdminForm;
import hr.tvz.diplomski.pios_oorp.form.admin.CategoryVisibilityAdminForm;
import hr.tvz.diplomski.pios_oorp.service.CategoryService;
import hr.tvz.diplomski.pios_oorp.service.OrderService;
import hr.tvz.diplomski.pios_oorp.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.MessageFormat;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Resource
    private CategoryService categoryService;

    @Resource
    private ProductService productService;

    @Resource
    private OrderService orderService;

    /**
     * Shows administrator homepage with list of all {@link hr.tvz.diplomski.pios_oorp.domain.Order}
     * @param model model
     * @return admin home page
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String viewAdminHomepage(Model model) {
        model.addAttribute("title", "Pregled korisničkih narudžbi");
        model.addAttribute("orders", orderService.getAllOrders());
        return PagesConstants.ADMIN_HOMEPAGE;
    }

    /**
     * Creates new {@link hr.tvz.diplomski.pios_oorp.domain.Category}
     * @param form Form with details of {@link hr.tvz.diplomski.pios_oorp.domain.Category} which will be created
     * @return home page
     */
    @RequestMapping(value = "/category/add", method = RequestMethod.POST)
    public RedirectView addNewCategory(@Valid @ModelAttribute("addCategoryAdminForm")AddCategoryAdminForm form) {
        categoryService.createNewCategory(form.getParentCategoryId(), form.getCategoryName());
        return new RedirectView("/", true);
    }

    /**
     * Changes visibility of category
     * @param form Form with id of category for which visibility should be changed
     * @return home page
     */
    @RequestMapping(value = "/category/change-visibility", method = RequestMethod.POST)
    public RedirectView changeCategoryVisibility(@Valid @ModelAttribute("categoryVisibilityAdminForm") CategoryVisibilityAdminForm form) {
        categoryService.changeCategoryVisibility(form.getCategoryId());
        return new RedirectView("/", true);
    }

    /**
     * Updates {@link hr.tvz.diplomski.pios_oorp.domain.Product} with provided data
     * @param form Form with details of product which will be updated
     * @param redirectAttributes redirectAttributes
     * @return product, category or homepage
     */
    @RequestMapping(value = "/product/add-edit", method = RequestMethod.POST)
    public RedirectView addNewProductOrditExisting(@Valid @ModelAttribute("addNewProductForm") AddOrEditProductForm form,
                                                   RedirectAttributes redirectAttributes) {
        productService.createNewOrEditProductIfExists(form);
        if (form.getCategoryId() != null) {
            redirectAttributes.addFlashAttribute("alertMessage",
                    new AlertMessage("Uspješno ste dodali novi proizvod", AlertType.SUCCESS));
            return new RedirectView("/category/" + form.getCategoryId(), true);
        } else if (form.getProductId() != null) {
            redirectAttributes.addFlashAttribute("alertMessage",
                    new AlertMessage("Uspješno ste uredili proizvod", AlertType.SUCCESS));
            return new RedirectView("/product/" + form.getProductId(), true);
        } else {
            return new RedirectView("/");
        }
    }
}
