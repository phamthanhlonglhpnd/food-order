package polarbear.java.foodorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import polarbear.java.foodorder.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findOrdersByCustomerId(Long userId);

    public List<Order> findOrdersByRestaurantId(Long restaurantId);
}
