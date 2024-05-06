package polarbear.java.foodorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import polarbear.java.foodorder.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
