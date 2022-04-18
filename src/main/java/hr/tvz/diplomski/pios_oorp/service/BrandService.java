package hr.tvz.diplomski.pios_oorp.service;

import hr.tvz.diplomski.pios_oorp.domain.Brand;

import java.util.List;

public interface BrandService {
    /**
     * @param brandName {@link Brand} name which will be fetched from database or created if it does not exist
     * @return brand
     */
    Brand getBrandAndCreateNewIfNotExists(String brandName);

    /**
     * @param brandNames {@link Brand} names which will be fetched from database
     * @return List of brands
     */
    List<Brand> getBrandsForNames(List<String> brandNames);
}
