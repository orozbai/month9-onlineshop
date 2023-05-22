package com.example.market.service;

import com.example.market.entity.Basket;
import com.example.market.entity.Order;
import com.example.market.entity.User;
import com.example.market.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    final private OrderRepository orderRepository;

    public List<Order> getOrdersByIdUser(Integer id) {
        return orderRepository.getOrdersByIdUser(id);
    }

    public void saveOrder(int totalPrice, String address, User user, Basket basket) {
        LocalDateTime time = LocalDateTime.now();
        Order order = Order.builder()
                .delivery(address)
                .price(totalPrice)
                .orderTime(time)
                .basket(basket)
                .user(user)
                .build();
        orderRepository.save(order);
    }
}
