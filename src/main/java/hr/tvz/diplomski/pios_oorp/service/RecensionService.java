package hr.tvz.diplomski.pios_oorp.service;

import hr.tvz.diplomski.pios_oorp.domain.Product;
import hr.tvz.diplomski.pios_oorp.domain.Recension;
import hr.tvz.diplomski.pios_oorp.domain.User;

public interface RecensionService {
    /**
     * Creates new {@link Recension} for {@link Product}
     * @param product {@link Product} to add {@link Recension} to
     * @param text {@link Recension} text
     * @param user {@link User} who added recension
     * @return created {@link Recension}
     */
    Recension addNewRecensionForProduct(Product product, String text, User user);
}
