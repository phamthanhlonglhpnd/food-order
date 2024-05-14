package polarbear.java.foodorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import polarbear.java.foodorder.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
