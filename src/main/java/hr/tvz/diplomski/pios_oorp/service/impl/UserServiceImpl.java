package hr.tvz.diplomski.pios_oorp.service.impl;

import hr.tvz.diplomski.pios_oorp.domain.Cart;
import hr.tvz.diplomski.pios_oorp.domain.User;
import hr.tvz.diplomski.pios_oorp.enumeration.UserType;
import hr.tvz.diplomski.pios_oorp.form.RegisterForm;
import hr.tvz.diplomski.pios_oorp.repository.CartRepository;
import hr.tvz.diplomski.pios_oorp.repository.UserRepository;
import hr.tvz.diplomski.pios_oorp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service

public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private CartRepository cartRepository;

    @Override
    public User getLoggedUser() {
        return userRepository.findByEmailEquals(getLoggedUserUsername()).orElse(null);
    }

    @Override
    public boolean registerNewCustomer(RegisterForm registerForm) {
        // TODO check if user is registered
        User user = new User();
        user.setEmail(registerForm.getEmail());
        user.setFirstName(registerForm.getFirstName());
        user.setLastName(registerForm.getLastName());
        user.setPassword(BCrypt.hashpw(registerForm.getPassword(), BCrypt.gensalt(10)));
        user.setType(UserType.CUSTOMER);

        Cart cart = new Cart();
        cart.setCreationDate(new Date());
        user.setCart(cart);

        cartRepository.save(cart);
        userRepository.save(user);
        return true;
    }

    private String getLoggedUserUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails) {
            return ((UserDetails)authentication.getPrincipal()).getUsername();
        }
        return null;
    }
}
