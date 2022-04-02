package hr.tvz.diplomski.pios_oorp.service.impl;

import hr.tvz.diplomski.pios_oorp.domain.Product;
import hr.tvz.diplomski.pios_oorp.repository.ProductRepository;
import hr.tvz.diplomski.pios_oorp.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductRepository productRepository;

    @Override
    public Product getProductForId(Long id) {
        return productRepository.getById(id);
    }
}
