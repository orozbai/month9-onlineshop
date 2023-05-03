package com.example.market.controller;

import com.example.market.dto.ProductDto;
import com.example.market.mapper.ProductMapper;
import com.example.market.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class ProductController {
    final private ProductService productService;
    final private ProductMapper productMapper;

    @GetMapping("products/")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts()
                .stream()
                .map(productDto -> productMapper.fromProduct(productDto))
                .collect(Collectors.toList());
    }
}
