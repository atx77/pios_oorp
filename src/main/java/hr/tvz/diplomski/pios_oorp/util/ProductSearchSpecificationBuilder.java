package hr.tvz.diplomski.pios_oorp.util;

import hr.tvz.diplomski.pios_oorp.domain.Brand;
import hr.tvz.diplomski.pios_oorp.domain.Category;
import hr.tvz.diplomski.pios_oorp.domain.Product;
import hr.tvz.diplomski.pios_oorp.domain.Product_;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductSearchSpecificationBuilder {

    /**
     * @param categories list of {@link Category} for filtering
     * @param brands list of {@link Brand} for filtering
     * @param minPrice minimum price for filtering
     * @param maxPrice maximum price for filtering
     * @param isOnSale flag is {@link Product} on sale for filtering
     * @param searchString text to search in {@link Product} name
     * @return Specification
     */
    public Specification<Product> build(List<Category> categories, List<Brand> brands, BigDecimal minPrice,
                                                      BigDecimal maxPrice, boolean isOnSale, String searchString) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(productIsActive(root, query, criteriaBuilder));

            if (categories != null && !categories.isEmpty()) {
                predicates.add(productInAnyCategory(root, query, criteriaBuilder, categories));
            }

            if (StringUtils.isNotBlank(searchString)) {
                predicates.add(productNameContains(root, query, criteriaBuilder, searchString));
            }

            if (isOnSale) {
                predicates.add(productIsOnSale(root, query, criteriaBuilder));
            }

            if (brands != null && !brands.isEmpty()){
                predicates.add(productHasAnyBrand(root, query, criteriaBuilder, brands));
            }

            if (minPrice != null && minPrice.compareTo(BigDecimal.ZERO) > 0) {
                predicates.add(productPriceGreaterThanOrEqual(root, query, criteriaBuilder, minPrice));
            }

            if (maxPrice != null && maxPrice.compareTo(BigDecimal.ZERO) > 0) {
                predicates.add(productPriceLessThanOrEqual(root, query, criteriaBuilder, maxPrice));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    /**
     * @param root root
     * @param query query
     * @param criteriaBuilder criteriaBuilder
     * @param categories list of {@link Category}
     * @return predicate for filtering by {@link Category} list
     */
    private Predicate productInAnyCategory(Root<Product> root, CriteriaQuery query, CriteriaBuilder criteriaBuilder,
                                           List<Category> categories) {
        return criteriaBuilder.in(root.get(Product_.CATEGORY)).value(categories);
    }


    /**
     * @param root root
     * @param query query
     * @param criteriaBuilder criteriaBuilder
     * @param searchText text to search in {@link Product} name
     * @return predicate for filtering by {@link Product} name
     */
    private Predicate productNameContains(Root<Product> root, CriteriaQuery query, CriteriaBuilder criteriaBuilder,
                                          String searchText) {
        return criteriaBuilder.like(root.get(Product_.NAME), "%" + searchText + "%");
    }


    /**
     * @param root root
     * @param query query
     * @param criteriaBuilder criteriaBuilder
     * @return predicate for filtering by {@link Product} active property
     */
    private Predicate productIsActive(Root<Product> root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get(Product_.ACTIVE), true);
    }

    /**
     * @param root root
     * @param query query
     * @param criteriaBuilder criteriaBuilder
     * @param brands list of {@link Brand} to filter by
     * @return predicate for filtering by {@link Brand} list
     */
    private Predicate productHasAnyBrand(Root<Product> root, CriteriaQuery query, CriteriaBuilder criteriaBuilder,
                                         List<Brand> brands) {
        return criteriaBuilder.in(root.get(Product_.BRAND)).value(brands);
    }

    /**
     * @param root root
     * @param query query
     * @param criteriaBuilder criteriaBuilder
     * @param price price for filtering by {@link Product} regularPrice greater than or equal
     * @return predicate for filtering by {@link Product} regularPrice greater than or equal
     */
    private Predicate productPriceGreaterThanOrEqual(Root<Product> root, CriteriaQuery query, CriteriaBuilder criteriaBuilder,
                                                     BigDecimal price) {
        return criteriaBuilder.greaterThanOrEqualTo(root.get(Product_.REGULAR_PRICE), price);
    }


    /**
     * @param root root
     * @param query query
     * @param criteriaBuilder criteriaBuilder
     * @param price price for filtering by {@link Product} regularPrice less than or equal
     * @return predicate for filtering by {@link Product} regularPrice less than or equal
     */
    private Predicate productPriceLessThanOrEqual(Root<Product> root, CriteriaQuery query, CriteriaBuilder criteriaBuilder,
                                                  BigDecimal price) {
        return criteriaBuilder.lessThanOrEqualTo(root.get(Product_.REGULAR_PRICE), price);
    }

    /**
     * @param root root
     * @param query query
     * @param criteriaBuilder criteriaBuilder
     * @return predicate for filtering by {@link Product} is on sale
     */
    private Predicate productIsOnSale(Root<Product> root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.isNotNull(root.get(Product_.ACTION_PRICE));
    }
}
