package com.example.market.controller;

import com.example.market.dto.ProductDto;
import com.example.market.dto.ReviewDto;
import com.example.market.mapper.ProductMapper;
import com.example.market.mapper.ReviewMapper;
import com.example.market.service.ReviewService;
import com.example.market.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/product/")
public class ProductController {
    final private ProductService productService;
    final private ReviewService reviewService;
    final private ProductMapper productMapper;
    final private ReviewMapper reviewMapper;

    @GetMapping("all")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts()
                .stream()
                .map(productMapper::fromProduct)
                .collect(Collectors.toList());
    }

    @GetMapping("comment")
    public List<ReviewDto> getCommentsByIdProduct(@RequestParam(name = "productId", required = false) Integer id) {
        return reviewService.getReviewsById(id)
                .stream()
                .map(reviewMapper::fromReview)
                .collect(Collectors.toList());
    }

    @GetMapping("search")
    public List<ProductDto> getProductWithName(@RequestParam("name") String name,
                                               @RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(required = false, defaultValue = "4") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.findByNamePage(name, pageable)
                .stream()
                .map(productMapper::fromProduct)
                .collect(Collectors.toList());
    }

    @GetMapping("brand")
    public List<ProductDto> getProductWithBrand(@RequestParam(value = "name", required = false) String name,
                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "description", required = false) String description,
                                                @RequestParam(required = false, defaultValue = "4") int size,
                                                @RequestParam(value = "min", required = false) Integer min,
                                                @RequestParam(value = "max", required = false) Integer max) {
        Pageable pageable = PageRequest.of(page, size);
        List<ProductDto> list = new ArrayList<>();
        if (name != null && description != null && min != null && max != null) {
            list = productService.findByMaxMinNameDesc(name, description, min, max, pageable)
                    .stream()
                    .map(productMapper::fromProduct)
                    .collect(Collectors.toList());
        } else if (name != null && description != null) {
            list = productService.findByBrandAndDesc(name, description, pageable)
                    .stream()
                    .map(productMapper::fromProduct)
                    .collect(Collectors.toList());
        } else if (name != null && min != null && max != null) {
            list = productService.findByNameBetween(name, min, max, pageable)
                    .stream()
                    .map(productMapper::fromProduct)
                    .collect(Collectors.toList());
        } else if (description != null && min != null && max != null) {
            list = productService.findByDescBetween(description, min, max, pageable)
                    .stream()
                    .map(productMapper::fromProduct)
                    .collect(Collectors.toList());
        } else if (min != null && max != null) {
            list = productService.findByBetween(max, min, pageable)
                    .stream()
                    .map(productMapper::fromProduct)
                    .collect(Collectors.toList());
        } else if (name != null) {
            list = productService.findByBrandPage(name, pageable)
                    .stream()
                    .map(productMapper::fromProduct)
                    .collect(Collectors.toList());
        } else if (description != null) {
            list = productService.findByDesc(description, pageable)
                    .stream()
                    .map(productMapper::fromProduct)
                    .collect(Collectors.toList());
        }
        return list;
    }
}
