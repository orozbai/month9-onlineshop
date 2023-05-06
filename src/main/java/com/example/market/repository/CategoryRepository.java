package com.example.market.repository;

import com.example.market.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findFirst5ByIdIn(List<Long> ids);
}
