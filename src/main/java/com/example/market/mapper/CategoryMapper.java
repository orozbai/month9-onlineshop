package com.example.market.mapper;

import com.example.market.dto.CategoryDto;
import com.example.market.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public static CategoryDto fromCategory(Category category) {
        return CategoryDto.builder()
                .name(category.getCategory())
                .build();
    }
}
