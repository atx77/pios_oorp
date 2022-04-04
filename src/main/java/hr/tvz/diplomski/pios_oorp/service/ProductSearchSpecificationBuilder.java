package hr.tvz.diplomski.pios_oorp.service;

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

    public Specification<Product> build(List<Category> categories, List<Brand> brands, BigDecimal minPrice,
                                                      BigDecimal maxPrice, boolean isOnSale, String searchString) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

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

    private Predicate productInAnyCategory(Root<Product> root, CriteriaQuery query, CriteriaBuilder criteriaBuilder,
                                           List<Category> categories) {
        return criteriaBuilder.in(root.get(Product_.CATEGORY)).value(categories);
    }

    private Predicate productNameContains(Root<Product> root, CriteriaQuery query, CriteriaBuilder criteriaBuilder,
                                          String searchText) {
        return criteriaBuilder.like(root.get(Product_.NAME), "%" + searchText + "%");
    }

    private Predicate productIsActive(Root<Product> root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get(Product_.ACTIVE), true);
    }

    private Predicate productHasAnyBrand(Root<Product> root, CriteriaQuery query, CriteriaBuilder criteriaBuilder,
                                         List<Brand> brands) {
        return criteriaBuilder.in(root.get(Product_.BRAND)).value(brands);
    }

    private Predicate productPriceGreaterThanOrEqual(Root<Product> root, CriteriaQuery query, CriteriaBuilder criteriaBuilder,
                                                     BigDecimal price) {
        return criteriaBuilder.greaterThanOrEqualTo(root.get(Product_.REGULAR_PRICE), price);
    }

    private Predicate productPriceLessThanOrEqual(Root<Product> root, CriteriaQuery query, CriteriaBuilder criteriaBuilder,
                                                  BigDecimal price) {
        return criteriaBuilder.lessThanOrEqualTo(root.get(Product_.REGULAR_PRICE), price);
    }

    private Predicate productIsOnSale(Root<Product> root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.isNotNull(root.get(Product_.ACTION_PRICE));
    }
}
