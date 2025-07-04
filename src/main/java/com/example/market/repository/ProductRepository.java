package com.example.market.repository;

import com.example.market.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

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

    @Query("select p from Product as p where p.brand.brand like %:name% and p.description like %:description%")
    Page<Product> findByNameDescription(String name, String description, Pageable pageable);

    @Query("select p from Product as p where p.description like %:description%")
    Page<Product> findByDesc(String description, Pageable pageable);

    @Query("select p from Product as p where p.brand.brand like %:name% and p.description like %:description% and p.price " +
            " between :min and :max")
    Page<Product> findByMinMaxNameDesc(String name, String description, Integer min, Integer max, Pageable pageable);

    @Query("select p from Product as p where p.price between :min and :max ")
    Page<Product> findByPriceBetweenPage(Integer min, Integer max, Pageable pageable);

    @Query("select p from Product as p where p.name like %:name% and p.price between :min and :max")
    Page<Product> findByNameAndBetween(String name, Integer min, Integer max, Pageable pageable);

    @Query("select p from Product as p where p.description like :description and p.price between :min and :max")
    Page<Product> findByDescBetween(String description, Integer min, Integer max, Pageable pageable);

    @Query("select p from Product as p where p.id = :id")
    Optional<Product> getProductById(int id);
    @Query("select p from Product as p where p.id = :num")
    Product findProductById(int num);
}
