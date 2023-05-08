package com.example.market.mapper;

import com.example.market.dto.OrderDto;
import com.example.market.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    public static OrderDto fromOrder(Order order) {
        return OrderDto.builder()
                .orderTime(order.getOrderTime())
                .delivery(order.getDelivery())
                .userEmail(order.getUser().getEmail())
                .price(order.getPrice())
                .productName(List.of(order.getProduct().getName()))
                .productPrice(order.getProduct().getPrice())
                .build();
    }
}
