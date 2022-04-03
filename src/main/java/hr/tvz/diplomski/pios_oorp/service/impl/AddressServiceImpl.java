package hr.tvz.diplomski.pios_oorp.service.impl;

import hr.tvz.diplomski.pios_oorp.domain.Address;
import hr.tvz.diplomski.pios_oorp.domain.User;
import hr.tvz.diplomski.pios_oorp.enumeration.CountryEnum;
import hr.tvz.diplomski.pios_oorp.repository.AddressRepository;
import hr.tvz.diplomski.pios_oorp.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressRepository addressRepository;

    @Override
    public Address createNewAddressForUser(String street, String city, String postcode,
                                           CountryEnum country, User user) {
        Address address = new Address();
        address.setUser(user);
        address.setCountry(country);
        address.setCity(city);
        address.setPostcode(postcode);
        address.setStreet(street);
        addressRepository.save(address);
        return address;
    }
}
