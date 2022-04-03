package hr.tvz.diplomski.pios_oorp.controller;

import hr.tvz.diplomski.pios_oorp.constant.PagesConstants;
import hr.tvz.diplomski.pios_oorp.form.AddNewProductForm;
import hr.tvz.diplomski.pios_oorp.form.admin.AddCategoryAdminForm;
import hr.tvz.diplomski.pios_oorp.form.admin.CategoryVisibilityAdminForm;
import hr.tvz.diplomski.pios_oorp.service.CategoryService;
import hr.tvz.diplomski.pios_oorp.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Resource
    private CategoryService categoryService;

    @Resource
    private ProductService productService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String viewAdminHomepage(Model model) {
        model.addAttribute("title", "admin homepage");
        return PagesConstants.ADMIN_HOMEPAGE;
    }

    @RequestMapping(value = "/category/add", method = RequestMethod.POST)
    public RedirectView addNewCategory(@Valid @ModelAttribute("addCategoryAdminForm")AddCategoryAdminForm form) {
        categoryService.createNewCategory(form.getParentCategoryId(), form.getCategoryName());
        return new RedirectView("/", true);
    }

    @RequestMapping(value = "/category/change-visibility", method = RequestMethod.POST)
    public RedirectView changeCategoryVisibility(@Valid @ModelAttribute("categoryVisibilityAdminForm") CategoryVisibilityAdminForm form) {
        categoryService.changeCategoryVisibility(form.getCategoryId());
        return new RedirectView("/", true);
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    public RedirectView addNewProduct(@Valid @ModelAttribute("addNewProductForm")AddNewProductForm form) {
        productService.createNewProduct(form);
        return new RedirectView("/category/" + form.getCategoryId(), true);
    }
}
