package hr.tvz.diplomski.pios_oorp.service;

import hr.tvz.diplomski.pios_oorp.domain.Product;
import hr.tvz.diplomski.pios_oorp.form.AddNewProductForm;

import java.util.Optional;

public interface ProductService {
    Optional<Product> getProductForId(Long id);
    Product createNewProduct(AddNewProductForm productForm);
}
