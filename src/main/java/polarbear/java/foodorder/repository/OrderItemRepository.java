package polarbear.java.foodorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import polarbear.java.foodorder.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
