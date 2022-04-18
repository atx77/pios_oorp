package hr.tvz.diplomski.pios_oorp.controller;

import hr.tvz.diplomski.pios_oorp.constant.PagesConstants;
import hr.tvz.diplomski.pios_oorp.domain.Category;
import hr.tvz.diplomski.pios_oorp.domain.Product;
import hr.tvz.diplomski.pios_oorp.enumeration.SortType;
import hr.tvz.diplomski.pios_oorp.form.AddOrEditProductForm;
import hr.tvz.diplomski.pios_oorp.form.AddToCartForm;
import hr.tvz.diplomski.pios_oorp.service.CategoryService;
import hr.tvz.diplomski.pios_oorp.service.ProductService;
import hr.tvz.diplomski.pios_oorp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @Resource
    private UserService userService;

    @Resource
    private ProductService productService;

    /**
     * Shows category page
     * @param categoryId Id of {@link Category} to show
     * @param brands List of {@link hr.tvz.diplomski.pios_oorp.domain.Brand} for filtering
     * @param minPrice Minimum price for filtering
     * @param maxPrice Maximum price for filtering
     * @param isOnSale Flag is {@link Product} on sale for filtering
     * @param sortType sorting type
     * @param model model
     * @return category page
     */
    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    public String viewCategoryPage(@PathVariable("categoryId") Long categoryId,
                                   @RequestParam(value = "brand", required = false) final List<String> brands,
                                   @RequestParam(value = "minPrice", required = false) final BigDecimal minPrice,
                                   @RequestParam(value = "maxPrice", required = false) final BigDecimal maxPrice,
                                   @RequestParam(value = "isOnSale", required = false, defaultValue = "false") final boolean isOnSale,
                                   @RequestParam(value = "sort", required = false) final SortType sortType,
                                   Model model) {
        Category category = categoryService.getCategoryById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category " + categoryId + " not found!"));
        if (!category.isActive() && !userService.isSessionUserAdmin()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category " + categoryId + " not found!");
        }

        model.addAttribute("category", category);
        model.addAttribute("title", category.getName());
        List<Product> products = productService.findAllProductsInCategoryAndFilter(category, brands, minPrice, maxPrice, isOnSale, sortType);
        model.addAttribute("productSearchResults", products);
        model.addAttribute("productBrands", productService.getBrandsForProducts(products));
        model.addAttribute("addToCartForm", new AddToCartForm());
        model.addAttribute("sortTypes", SortType.values());

        model.addAttribute("filteredBrands", brands != null ? brands.stream().collect(Collectors.joining(";")) : "");
        model.addAttribute("filteredIsOnSale", isOnSale);
        model.addAttribute("filteredMinPrice", minPrice);
        model.addAttribute("filteredMaxPrice", maxPrice);
        model.addAttribute("chosenSort", sortType != null ? sortType : SortType.DATE_ADDED_DESC);

        if (userService.isSessionUserAdmin()) {
            model.addAttribute("addNewProductForm", new AddOrEditProductForm());
        }

        return PagesConstants.CATEGORY;
    }
}
