package polarbear.java.foodorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import polarbear.java.foodorder.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
