package com.orderservice.orderservice.order;

import com.orderservice.orderservice.UserProducts;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orderProducts/{productId}/{userId}")
    public Order orderProducts(@PathVariable  UUID productId, @PathVariable UUID userId) {
     Order order = orderService.saveOrder(productId,userId);
     return order;
    }

    @GetMapping("/userProducts/{userId}")
    public UserProducts userProducts(@PathVariable UUID userId){
        List<Order> userOrderProducts = orderService.getOrdersByUserId(userId);
        UserProducts orderedProducts = new UserProducts();
        orderedProducts.setAllProducts(userOrderProducts);
        return orderedProducts;
    }
}
