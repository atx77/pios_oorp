package hr.tvz.diplomski.pios_oorp.repository;

import hr.tvz.diplomski.pios_oorp.domain.Order;
import hr.tvz.diplomski.pios_oorp.domain.Product;
import hr.tvz.diplomski.pios_oorp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByCodeEquals(String code);
    List<Order> findAllByUserEqualsOrderByCreationDateDesc(User user);
    List<Order> findAllByUserEqualsAndItems_ProductEquals(User user, Product product);
}
