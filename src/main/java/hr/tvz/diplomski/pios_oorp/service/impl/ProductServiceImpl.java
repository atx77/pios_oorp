package hr.tvz.diplomski.pios_oorp.service.impl;

import hr.tvz.diplomski.pios_oorp.domain.Brand;
import hr.tvz.diplomski.pios_oorp.domain.Category;
import hr.tvz.diplomski.pios_oorp.domain.Product;
import hr.tvz.diplomski.pios_oorp.enumeration.SortType;
import hr.tvz.diplomski.pios_oorp.form.AddOrEditProductForm;
import hr.tvz.diplomski.pios_oorp.repository.ProductRepository;
import hr.tvz.diplomski.pios_oorp.service.BrandService;
import hr.tvz.diplomski.pios_oorp.service.CategoryService;
import hr.tvz.diplomski.pios_oorp.service.ProductService;
import hr.tvz.diplomski.pios_oorp.util.ProductSearchSortBuilder;
import hr.tvz.diplomski.pios_oorp.util.ProductSearchSpecificationBuilder;
import hr.tvz.diplomski.pios_oorp.util.PriceUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final PriceUtils priceUtils = new PriceUtils();

    @Resource
    private ProductRepository productRepository;

    @Resource
    private BrandService brandService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private ProductSearchSpecificationBuilder productSearchSpecificationBuilder;

    @Resource
    private ProductSearchSortBuilder productSearchSortBuilder;

    @Override
    public Optional<Product> getProductForId(Long id) {
        return Optional.ofNullable(productRepository.getById(id));
    }

    @Transactional
    @Override
    public Product createNewOrEditProductIfExists(AddOrEditProductForm productForm) {
        Assert.notNull(productForm.getName());
        Assert.notNull(productForm.getRegularPrice());

        Category category = productForm.getCategoryId() != null ? categoryService.getCategoryById(productForm.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category " + productForm.getCategoryId() + "does not exist!")) : null;
        Product existingProduct = productForm.getProductId() != null ? productRepository.getById(productForm.getProductId()) : null;

        Product product = new Product();
        if (existingProduct != null) {
            product = existingProduct;
        } else {
            product.setCategory(category);
            product.setCreationDate(new Date());
        }

        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        product.setSummary(productForm.getSummary());
        product.setRegularPrice(productForm.getRegularPrice());
        product.setActionPrice(productForm.getActionPrice());
        product.setDiscountPercentage(priceUtils.calculateDiscountPercentage(productForm.getRegularPrice(), productForm.getActionPrice()));
        product.setAvailableQuantity(1000);
        product.setActive(productForm.isActive());
        product.setImageUrl(productForm.getImageUrl());
        product.setBrand(brandService.getBrandAndCreateNewIfNotExists(productForm.getBrand()));
        productRepository.save(product);
        return product;
    }

    @Override
    public List<Product> findAllProductsInCategoryAndFilter(Category category, List<String> brandNames, BigDecimal minPrice,
                                                            BigDecimal maxPrice, boolean isOnSale, SortType sortType) {
        List<Category> parentAndChildCategories = new ArrayList<>();
        parentAndChildCategories.add(category);
        parentAndChildCategories.addAll(category.getSubCategories());

        List<Brand> brands = new ArrayList<>();
        if (brandNames != null) {
            brands = brandService.getBrandsForNames(brandNames);
        }

        Specification<Product> productSpecification = productSearchSpecificationBuilder.build(parentAndChildCategories,
                brands, minPrice, maxPrice, isOnSale, null);
        Sort sort = productSearchSortBuilder.build(sortType);

        return productRepository.findAll(productSpecification, sort);
    }

    @Override
    public List<Brand> getBrandsForProducts(List<Product> products) {
        return products.stream().map(Product::getBrand).filter(Objects::nonNull).distinct().collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllProductsByTextAndFilter(String searchText, List<String> brandNames, BigDecimal minPrice, BigDecimal maxPrice, boolean isOnSale, SortType sortType) {
        List<Brand> brands = new ArrayList<>();
        if (brandNames != null) {
            brands = brandService.getBrandsForNames(brandNames);
        }

        Specification<Product> productSpecification = productSearchSpecificationBuilder.build(null,
                brands, minPrice, maxPrice, isOnSale, searchText);
        Sort sort = productSearchSortBuilder.build(sortType);

        return productRepository.findAll(productSpecification, sort);
    }

    @Override
    public List<Product> findNewestProducts() {
        return productRepository.findTop10ByOrderByCreationDateDesc();
    }
}
