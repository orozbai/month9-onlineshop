package com.example.market.mapper;

import com.example.market.dto.OrderDto;
import com.example.market.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public static OrderDto fromOrder(Order order) {
        return OrderDto.builder()
                .orderTime(order.getOrderTime())
                .delivery(order.getDelivery())
                .userEmail(order.getUser().getEmail())
                .price(order.getPrice())
                .productName(order.getProduct().getName())
                .productPrice(order.getProduct().getPrice())
                .build();
    }
}
