package com.example.market.repository;

import com.example.market.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    @Query(value = "select b from Brand as b")
    List<Brand> findAllBrand();
}
