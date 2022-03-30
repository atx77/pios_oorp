package hr.tvz.diplomski.pios_oorp.controller;

import hr.tvz.diplomski.pios_oorp.constant.PagesConstants;
import hr.tvz.diplomski.pios_oorp.domain.Category;
import hr.tvz.diplomski.pios_oorp.repository.CategoryRepository;
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
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    public String viewCategoryPage(@PathVariable("categoryId") Long categoryId, Model model) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category " + categoryId + " not found!"));
        model.addAttribute("category", "login page");
        model.addAttribute("title", category.getName());
        return PagesConstants.CATEGORY;
    }
}
