package com.example.market.service;

import com.example.market.entity.Category;
import com.example.market.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    final private CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        return categoryRepository.findAllBy();
    }
}
