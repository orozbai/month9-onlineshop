package com.example.market.repository;

import com.example.market.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    Basket findFirstByOrderByIdDesc();

    @Query("select b.products from Basket as b where b.identification like :uni")
    String findWithUni(String uni);
}
