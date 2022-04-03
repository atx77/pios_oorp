package hr.tvz.diplomski.pios_oorp.service;

import hr.tvz.diplomski.pios_oorp.domain.Brand;

public interface BrandService {
    Brand getBrandAndCreateNewIfNotExists(String brandName);
}
