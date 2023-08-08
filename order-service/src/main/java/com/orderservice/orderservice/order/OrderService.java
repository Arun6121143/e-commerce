package com.orderservice.orderservice.order;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(UUID productId, UUID userId) {
        Order order = new Order();
        order.setProductId(productId);
        order.setUserId(userId);
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUserId(UUID userId) {
        return orderRepository.findByUserId(userId);
    }
}
