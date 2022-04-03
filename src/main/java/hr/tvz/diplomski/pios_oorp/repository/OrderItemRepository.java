package hr.tvz.diplomski.pios_oorp.repository;

import hr.tvz.diplomski.pios_oorp.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
