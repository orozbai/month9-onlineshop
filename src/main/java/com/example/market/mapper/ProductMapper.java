package com.example.market.mapper;

import com.example.market.dto.ProductDto;
import com.example.market.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto fromProduct(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .description(product.getDescription())
                .count(product.getCount())
                .price(product.getPrice())
                .brand(product.getBrand().getBrand())
                .category(product.getCategory().getCategory())
                .image(product.getImage())
                .id(product.getId())
                .build();
    }
}
