package hr.tvz.diplomski.pios_oorp.service;

import hr.tvz.diplomski.pios_oorp.domain.Brand;

import java.util.List;

public interface BrandService {
    Brand getBrandAndCreateNewIfNotExists(String brandName);
    List<Brand> getBrandsForNames(List<String> brandNames);
}
