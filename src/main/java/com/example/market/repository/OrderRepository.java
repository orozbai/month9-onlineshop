package com.example.market.repository;

import com.example.market.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select o from Order as o where o.user.id = :id")
    List<Order> getOrdersByIdUser(Integer id);

//    @Query(nativeQuery = true, value = "insert into orders (delivery, price, order_time, user_id, basket_id)  " +
//            "values (:address, :totalPrice, :time, :userId, :basketId)")
//    void saveNewOrder(int totalPrice, String address, int userId, LocalDateTime time, int basketId);
}
