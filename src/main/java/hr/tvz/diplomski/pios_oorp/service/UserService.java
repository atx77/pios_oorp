package hr.tvz.diplomski.pios_oorp.service;

import hr.tvz.diplomski.pios_oorp.domain.Product;
import hr.tvz.diplomski.pios_oorp.domain.User;
import hr.tvz.diplomski.pios_oorp.form.RegisterForm;

public interface UserService {
    User getLoggedUser();
    boolean registerNewCustomer(RegisterForm registerForm);
    boolean isSessionUserAdmin();
    boolean isSessionUserGuest();
    boolean isSessionUserCustomer();
    void updateUserPersonalInformation(String firstName, String lastName, String password);
    boolean hasUserBoughtProduct(User user, Product product);
}
