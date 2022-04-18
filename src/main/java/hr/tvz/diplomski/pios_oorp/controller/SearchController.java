package hr.tvz.diplomski.pios_oorp.controller;

import hr.tvz.diplomski.pios_oorp.constant.PagesConstants;
import hr.tvz.diplomski.pios_oorp.domain.Product;
import hr.tvz.diplomski.pios_oorp.enumeration.SortType;
import hr.tvz.diplomski.pios_oorp.form.AddToCartForm;
import hr.tvz.diplomski.pios_oorp.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/search")
public class SearchController {

    @Resource
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public String viewSearchPage(@RequestParam(value = "text", required = false) final String searchText,
                                 @RequestParam(value = "brand", required = false) final List<String> brands,
                                 @RequestParam(value = "minPrice", required = false) final BigDecimal minPrice,
                                 @RequestParam(value = "maxPrice", required = false) final BigDecimal maxPrice,
                                 @RequestParam(value = "isOnSale", required = false, defaultValue = "false") final boolean isOnSale,
                                 @RequestParam(value = "sort", required = false) final SortType sortType,
                                 Model model) {
        if (searchText == null || searchText.isBlank()) {
            return PagesConstants.HOMEPAGE;
        }
        model.addAttribute("title", MessageFormat.format("Pretraga \"{0}\"", searchText));
        model.addAttribute("searchText", searchText);

        List<Product> products = productService.findAllProductsByTextAndFilter(searchText, brands, minPrice, maxPrice, isOnSale, sortType);
        model.addAttribute("productSearchResults", products);
        model.addAttribute("productBrands", productService.getBrandsForProducts(products));
        model.addAttribute("addToCartForm", new AddToCartForm());
        model.addAttribute("sortTypes", SortType.values());

        model.addAttribute("filteredBrands", brands != null ? brands.stream().collect(Collectors.joining(";")) : "");
        model.addAttribute("filteredIsOnSale", isOnSale);
        model.addAttribute("filteredMinPrice", minPrice);
        model.addAttribute("filteredMaxPrice", maxPrice);
        model.addAttribute("chosenSort", sortType != null ? sortType : SortType.DATE_ADDED_DESC);

        return PagesConstants.SEARCH;
    }
}
