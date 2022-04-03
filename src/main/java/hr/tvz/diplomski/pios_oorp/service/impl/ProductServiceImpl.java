package hr.tvz.diplomski.pios_oorp.service.impl;

import hr.tvz.diplomski.pios_oorp.domain.Category;
import hr.tvz.diplomski.pios_oorp.domain.Product;
import hr.tvz.diplomski.pios_oorp.form.AddNewProductForm;
import hr.tvz.diplomski.pios_oorp.repository.ProductRepository;
import hr.tvz.diplomski.pios_oorp.service.BrandService;
import hr.tvz.diplomski.pios_oorp.service.CategoryService;
import hr.tvz.diplomski.pios_oorp.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductRepository productRepository;

    @Resource
    private BrandService brandService;

    @Resource
    private CategoryService categoryService;

    @Override
    public Product getProductForId(Long id) {
        return productRepository.getById(id);
    }

    @Transactional
    @Override
    public Product createNewProduct(AddNewProductForm productForm) {
        Assert.notNull(productForm.getCategoryId());
        Assert.notNull(productForm.getName());
        Assert.notNull(productForm.getRegularPrice());

        Category category = categoryService.getCategoryById(productForm.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category " + productForm.getCategoryId() + "does not exist!"));

        Product product = new Product();
        product.setCategory(category);
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        product.setSummary(productForm.getSummary());
        product.setRegularPrice(productForm.getRegularPrice());
        product.setActionPrice(productForm.getActionPrice());
        product.setDiscountPercentage(calculateDiscount(productForm.getRegularPrice(), productForm.getActionPrice()));
        product.setAvailableQuantity(1000);
        product.setActive(true);
        product.setImageUrl(productForm.getImageUrl());
        product.setBrand(brandService.getBrandAndCreateNewIfNotExists(productForm.getBrand()));
        product.setCreationDate(new Date());
        productRepository.save(product);
        return product;
    }

    private BigDecimal calculateDiscount(BigDecimal regularPrice, BigDecimal actionPrice) {
        if (actionPrice == null) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.ONE.subtract(actionPrice.divide(regularPrice, 2, RoundingMode.HALF_UP))
                .multiply(new BigDecimal(100))
                .setScale(0, RoundingMode.HALF_UP);
    }
}
