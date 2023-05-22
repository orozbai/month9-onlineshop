package com.example.market.mapper;

import com.example.market.dto.OrderDto;
import com.example.market.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    public OrderDto fromOrder(Order order) {
        return OrderDto.builder()
                .orderTime(order.getOrderTime())
                .delivery(order.getDelivery())
                .userEmail(order.getUser().getEmail())
                .price(order.getPrice())
                .productName(order.getBasket().getProducts())
                .productPrice(order.getPrice())
                .build();
    }
}
