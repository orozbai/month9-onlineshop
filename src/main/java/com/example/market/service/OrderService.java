package com.example.market.service;

import com.example.market.entity.Order;
import com.example.market.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    final private OrderRepository orderRepository;

    public List<Order> getOrdersByIdUser(Integer id) {
        return orderRepository.getOrdersByIdUser(id);
    }
}
