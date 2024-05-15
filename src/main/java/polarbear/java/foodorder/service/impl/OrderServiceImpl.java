package polarbear.java.foodorder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import polarbear.java.foodorder.model.*;
import polarbear.java.foodorder.repository.AddressRepository;
import polarbear.java.foodorder.repository.OrderItemRepository;
import polarbear.java.foodorder.repository.OrderRepository;
import polarbear.java.foodorder.repository.UserRepository;
import polarbear.java.foodorder.request.OrderRequest;
import polarbear.java.foodorder.service.CartService;
import polarbear.java.foodorder.service.OrderService;
import polarbear.java.foodorder.service.RestaurantService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(OrderRequest order, User user) throws Exception {
        Address shipAddress = order.getDeliveryAddress();
        Address savedAddress = addressRepository.save(shipAddress);

        if(!user.getAddresses().contains(savedAddress)) {
            user.getAddresses().add(savedAddress);
            userRepository.save(user);
        }
        Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());
        Order createdOrder = new Order();
        createdOrder.setCustomer(user);
        createdOrder.setCreatedAt(new Date());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.setDeliveryAddress(savedAddress);
        createdOrder.setRestaurant(restaurant);

        Cart cart = cartService.findCartByUserId(user.getId());
        List<OrderItem> orderItems = new ArrayList<>();

        for(CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);
        }
        createdOrder.setItems(orderItems);
        createdOrder.setTotalPrice(cartService.calculateCartTotal(cart));

        Order savedOrder = orderRepository.save(createdOrder);
        restaurant.getOrders().add(savedOrder);

        return savedOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order = findOrderById(orderId);
        if(orderStatus.equals("OUT_FOR_DELIVERY")
            || orderStatus.equals("DELIVERED")
                || orderStatus.equals("COMPLETED")
                || orderStatus.equals("PENDING")
        ) {
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new Exception("Please choose a valid order status");
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order = findOrderById(orderId);
        orderRepository.delete(order);
    }

    @Override
    public List<Order> getUserOrders(Long userId) throws Exception {
        return orderRepository.findOrdersByCustomerId(userId);
    }

    @Override
    public List<Order> getRestaurantOrders(long restaurantId, String orderStatus) throws Exception {
        List<Order> orders = orderRepository.findOrdersByRestaurantId(restaurantId);
        if(orderStatus != null) {
            orders = orders.stream().filter(order -> order.getOrderStatus()
                            .equals(orderStatus)).collect(Collectors.toList());
        }
        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isEmpty()) {
            throw new Exception("Order is not found");
        }
        return order.get();
    }
}
