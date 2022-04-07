package hr.tvz.diplomski.pios_oorp.service;

import hr.tvz.diplomski.pios_oorp.domain.Product;
import hr.tvz.diplomski.pios_oorp.domain.Recension;
import hr.tvz.diplomski.pios_oorp.domain.User;

public interface RecensionService {
    Recension addNewRecensionForProduct(Product product, String text, User user);
}
