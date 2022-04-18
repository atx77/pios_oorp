package hr.tvz.diplomski.pios_oorp.repository;

import hr.tvz.diplomski.pios_oorp.domain.Cart;
import hr.tvz.diplomski.pios_oorp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
