package hr.tvz.diplomski.pios_oorp.controller;

import hr.tvz.diplomski.pios_oorp.constant.PagesConstants;
import hr.tvz.diplomski.pios_oorp.domain.Category;
import hr.tvz.diplomski.pios_oorp.form.AddToCartForm;
import hr.tvz.diplomski.pios_oorp.service.CategoryService;
import hr.tvz.diplomski.pios_oorp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @Resource
    private UserService userService;

    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    public String viewCategoryPage(@PathVariable("categoryId") Long categoryId, Model model) {
        Category category = categoryService.getCategoryById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category " + categoryId + " not found!"));
        if (!category.isActive() && !userService.isSessionUserAdmin()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category " + categoryId + " not found!");
        }

        model.addAttribute("category", category);
        model.addAttribute("title", category.getName());
        model.addAttribute("addToCartForm", new AddToCartForm());
        return PagesConstants.CATEGORY;
    }
}
