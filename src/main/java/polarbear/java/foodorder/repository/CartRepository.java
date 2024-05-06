package polarbear.java.foodorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polarbear.java.foodorder.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
