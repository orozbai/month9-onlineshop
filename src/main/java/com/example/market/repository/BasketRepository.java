package com.example.market.repository;

import com.example.market.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    Basket findFirstByOrderByIdDesc();
}
