package hr.tvz.diplomski.pios_oorp.repository;

import hr.tvz.diplomski.pios_oorp.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
