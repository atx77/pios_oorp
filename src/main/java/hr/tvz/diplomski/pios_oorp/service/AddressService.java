package hr.tvz.diplomski.pios_oorp.service;

import hr.tvz.diplomski.pios_oorp.domain.Address;
import hr.tvz.diplomski.pios_oorp.domain.User;
import hr.tvz.diplomski.pios_oorp.enumeration.CountryEnum;

public interface AddressService {
    Address createNewAddressForUser(String street, String city, String postcode, CountryEnum country, User user);
}
