package hr.tvz.diplomski.pios_oorp.service;

import hr.tvz.diplomski.pios_oorp.domain.Product;
import hr.tvz.diplomski.pios_oorp.domain.User;
import hr.tvz.diplomski.pios_oorp.form.RegisterForm;

public interface UserService {
    /**
     * @return User from session
     */
    User getLoggedUser();

    /**
     * Creates new {@link User}
     * @param registerForm Details about {@link User} to create
     * @return registered {@link User}
     */
    boolean registerNewCustomer(RegisterForm registerForm);

    /**
     * @return boolean that defines if {@link User} is admin
     */
    boolean isSessionUserAdmin();

    /**
     * @return boolean that defines if {@link User} is guest
     */
    boolean isSessionUserGuest();

    /**
     * @return boolean that defines if {@link User} is customer
     */
    boolean isSessionUserCustomer();

    /**
     * Updates firstName, lastName and password of session {@link User}
     * @param firstName first name to set to {@link User}
     * @param lastName last name to set to {@link User}
     * @param password password to hash and set to {@link User}
     */
    void updateUserPersonalInformation(String firstName, String lastName, String password);

    /**
     * Checks if {@link User} has ever bought provided {@link Product}
     * @param user {@link User} to check
     * @param product {@link Product} to check
     * @return boolean which defines if provided {@link User} has ever bought provided {@link Product}
     */
    boolean hasUserBoughtProduct(User user, Product product);
}
