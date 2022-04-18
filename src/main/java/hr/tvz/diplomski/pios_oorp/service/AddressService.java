package hr.tvz.diplomski.pios_oorp.service;

import hr.tvz.diplomski.pios_oorp.domain.Address;
import hr.tvz.diplomski.pios_oorp.domain.User;
import hr.tvz.diplomski.pios_oorp.enumeration.CountryEnum;

public interface AddressService {
    /**
     * Creates new {@link Address} for provided {@link User}
     * @param street Street name
     * @param city City name
     * @param postcode Postcode
     * @param country {@link CountryEnum}
     * @param user {@link Address} owner
     * @return created {@link Address}
     */
    Address createNewAddressForUser(String street, String city, String postcode, CountryEnum country, User user);
}
