package hr.tvz.diplomski.pios_oorp.repository;

import hr.tvz.diplomski.pios_oorp.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
