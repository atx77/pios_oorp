package hr.tvz.diplomski.pios_oorp.controller;

import hr.tvz.diplomski.pios_oorp.constant.PagesConstants;
import hr.tvz.diplomski.pios_oorp.form.admin.AddCategoryAdminForm;
import hr.tvz.diplomski.pios_oorp.form.admin.CategoryVisibilityAdminForm;
import hr.tvz.diplomski.pios_oorp.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Resource
    private CategoryService categoryService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String viewAdminHomepage(Model model) {
        model.addAttribute("title", "admin homepage");
        return PagesConstants.ADMIN_HOMEPAGE;
    }

    @RequestMapping(value = "/category/add", method = RequestMethod.POST)
    public RedirectView addNewCategory(@ModelAttribute("addCategoryAdminForm")AddCategoryAdminForm form) {
        categoryService.createNewCategory(form.getParentCategoryId(), form.getCategoryName());
        return new RedirectView("/", true);
    }

    @RequestMapping(value = "/category/change-visibility", method = RequestMethod.POST)
    public RedirectView addNewCategory(@ModelAttribute("categoryVisibilityAdminForm") CategoryVisibilityAdminForm form) {
        categoryService.changeCategoryVisibility(form.getCategoryId());
        return new RedirectView("/", true);
    }
}
