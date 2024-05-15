package polarbear.java.foodorder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polarbear.java.foodorder.model.Order;
import polarbear.java.foodorder.service.OrderService;
import polarbear.java.foodorder.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/history-restaurant-orders/{id}")
    public ResponseEntity<List<Order>> getHistoryOrder(
            @PathVariable Long id,
            @RequestParam(required = false) String order_status
    ) throws Exception {
        List<Order> orders = orderService.getRestaurantOrders(id, order_status);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/update-order-status/{id}")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam(required = false) String order_status
    ) throws Exception {
        Order order = orderService.updateOrder(id, order_status);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
