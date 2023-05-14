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
                .map(productDto -> productMapper.fromProduct(productDto))
                .collect(Collectors.toList());
    }

    @GetMapping("comment")
    public List<ReviewDto> getCommentsByIdProduct(@RequestParam(name = "productId", required = false) Integer id) {
        return reviewService.getReviewsById(id)
                .stream()
                .map(reviews -> reviewMapper.fromReview(reviews))
                .collect(Collectors.toList());
    }

    @GetMapping("search")
    public List<ProductDto> getProductWithName(@RequestParam("name") String name,
                                               @RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(required = false, defaultValue = "4") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.findByNamePage(name, pageable)
                .stream()
                .map(product -> productMapper.fromProduct(product))
                .collect(Collectors.toList());
    }
}
