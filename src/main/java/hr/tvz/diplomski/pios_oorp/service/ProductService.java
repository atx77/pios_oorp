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
    /**
     * Fetches {@link Product} by id
     * @param id Id of {@link Product} to fetch
     * @return fetched {@link Product}
     */
    Optional<Product> getProductForId(Long id);

    /**
     * Adds new {@link Product} if it does not exist or edits existing {@link Product} with data from form
     * @param productForm Form with details of {@link Product} to add or edit
     * @return created/edited {@link Product}
     */
    Product createNewOrEditProductIfExists(AddOrEditProductForm productForm);

    /**
     * Filters all {@link Product} by {@link Category} and other filters
     * @param category {@link Category} for filtering
     * @param brandNames {@link Brand} names for filtering
     * @param minPrice minimum price for filtering
     * @param maxPrice maximum price for filtering
     * @param isOnSale flag is {@link Product} on sale for filtering
     * @param sortType {@link SortType}
     * @return list with filtered products
     */
    List<Product> findAllProductsInCategoryAndFilter(Category category, List<String> brandNames, BigDecimal minPrice,
                                                     BigDecimal maxPrice, boolean isOnSale, SortType sortType);

    /**
     * Fetches list of all {@link Brand} for provided list of {@link Product}
     * @param products list of {@link Product} to find list of {@link Brand} for
     * @return list of all {@link Brand} for provided list of {@link Product}
     */
    List<Brand> getBrandsForProducts(List<Product> products);

    /**
     * Filters all {@link Product} by search text and other filters
     * @param searchText text to search in {@link Product} name for filtering
     * @param brandNames {@link Brand} names for filtering
     * @param minPrice minimum price for filtering
     * @param maxPrice maximum price for filtering
     * @param isOnSale flag is {@link Product} on sale for filtering
     * @param sortType {@link SortType}
     * @return list with filtered {@link Product}
     */
    List<Product> findAllProductsByTextAndFilter(String searchText, List<String> brandNames, BigDecimal minPrice,
                                                 BigDecimal maxPrice, boolean isOnSale, SortType sortType);

    /**
     * @return list of newest {@link Product}
     */
    List<Product> findNewestProducts();
}
