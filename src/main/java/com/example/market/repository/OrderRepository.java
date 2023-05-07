package com.example.market.repository;

import com.example.market.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select o from Order as o where o.user.id = :id")
    List<Order> getOrdersByIdUser(Integer id);
}
