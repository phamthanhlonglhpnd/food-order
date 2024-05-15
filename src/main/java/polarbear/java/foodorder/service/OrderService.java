package polarbear.java.foodorder.service;

import polarbear.java.foodorder.model.Order;
import polarbear.java.foodorder.model.User;
import polarbear.java.foodorder.request.OrderRequest;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderRequest order, User user) throws Exception;

    Order updateOrder(Long orderId, String orderStatus) throws Exception;

    void cancelOrder(Long orderId) throws Exception;

    List<Order> getUserOrders(Long userId) throws Exception;

    List<Order> getRestaurantOrders(long restaurantId, String orderStatus) throws Exception;

    Order findOrderById(Long orderId) throws Exception;
}
