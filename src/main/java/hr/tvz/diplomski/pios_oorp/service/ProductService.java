package hr.tvz.diplomski.pios_oorp.service;

import hr.tvz.diplomski.pios_oorp.domain.Brand;
import hr.tvz.diplomski.pios_oorp.domain.Category;
import hr.tvz.diplomski.pios_oorp.domain.Product;
import hr.tvz.diplomski.pios_oorp.enumeration.SortType;
import hr.tvz.diplomski.pios_oorp.form.AddOrEditProductForm;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getProductForId(Long id);
    Product createNewOrEditProductIfExists(AddOrEditProductForm productForm);
    List<Product> findAllProductsInCategoryAndFilter(Category category, List<String> brandNames, BigDecimal minPrice,
                                                     BigDecimal maxPrice, boolean isOnSale, SortType sortType);
    List<Brand> getBrandsForProducts(List<Product> products);
    List<Product> findAllProductsByTextAndFilter(String searchText, List<String> brandNames, BigDecimal minPrice, BigDecimal maxPrice, boolean isOnSale, SortType sortType);
}
