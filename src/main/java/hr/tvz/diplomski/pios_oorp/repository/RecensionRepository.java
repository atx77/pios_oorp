package hr.tvz.diplomski.pios_oorp.repository;

import hr.tvz.diplomski.pios_oorp.domain.Product;
import hr.tvz.diplomski.pios_oorp.domain.Recension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecensionRepository extends JpaRepository<Recension, Long> {
    List<Recension> findAllByProductEqualsOrderByCreationDateDesc(Product product);
}
