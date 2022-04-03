package hr.tvz.diplomski.pios_oorp.service.impl;

import hr.tvz.diplomski.pios_oorp.domain.Brand;
import hr.tvz.diplomski.pios_oorp.repository.BrandRepository;
import hr.tvz.diplomski.pios_oorp.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Resource
    private BrandRepository brandRepository;

    @Override
    public Brand getBrandAndCreateNewIfNotExists(String brandName) {
        if (StringUtils.isBlank(brandName)) {
            return null;
        }

        Optional<Brand> brandOptional = brandRepository.findByNameEquals(brandName.toUpperCase());
        if (brandOptional.isPresent()) {
            return brandOptional.get();
        }

        Brand brand = new Brand();
        brand.setName(brandName.toUpperCase());
        brandRepository.save(brand);

        return brand;
    }
}
