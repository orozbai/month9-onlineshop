package com.example.market.controller;

import com.example.market.dto.CategoryDto;
import com.example.market.mapper.CategoryMapper;
import com.example.market.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class CategoryController {
    final private CategoryService categoryService;
    final private CategoryMapper categoryMapper;

    @GetMapping("category/")
    public List<CategoryDto> getAllCategory() {
        return categoryService.getAllCategory().stream()
                .map(category -> categoryMapper.fromCategory(category))
                .collect(Collectors.toList());
    }
}
