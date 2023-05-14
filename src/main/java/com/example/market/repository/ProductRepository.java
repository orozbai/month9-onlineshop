package com.example.market.repository;

import com.example.market.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select p from Product as p")
    List<Product> findAllProducts();

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Product> findByCategory_Id(Long categoryId, Pageable pageable);

    Page<Product> findByPriceBetween(Integer minPrice, Integer maxPrice, Pageable pageable);

    Page<Product> findAllBy(Pageable pageable);

    @Query("select p from Product as p where p.name like %:name%")
    Page<Product> findByName(@Param("name") String name, Pageable pageable);
    @Query("select p from Product as p where p.brand.brand like %:name%")
    Page<Product> findByBrandPage(String name, Pageable pageable);
}
