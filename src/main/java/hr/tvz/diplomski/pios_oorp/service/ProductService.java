package hr.tvz.diplomski.pios_oorp.service;

import hr.tvz.diplomski.pios_oorp.domain.Product;

public interface ProductService {
    Product getProductForId(Long id);
}
