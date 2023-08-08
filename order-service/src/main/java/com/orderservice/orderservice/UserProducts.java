package com.orderservice.orderservice;

import com.orderservice.orderservice.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProducts {
    private List<Order> allProducts;
}
