package com.example.market.controller;

import com.example.market.dto.ProductDto;
import com.example.market.dto.ReviewDto;
import com.example.market.entity.Product;
import com.example.market.entity.Reviews;
import com.example.market.entity.User;
import com.example.market.mapper.ProductMapper;
import com.example.market.mapper.ReviewMapper;
import com.example.market.service.ReviewService;
import com.example.market.service.ProductService;
import com.example.market.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    final private UserService userService;

    @GetMapping("id")
    public ProductDto getProductWithId(@RequestParam(value = "id") int id) {
        return productService.findById(id)
                .stream()
                .map(productMapper::fromProduct)
                .collect(Collectors.toList()).get(0);
    }

    @GetMapping("all")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts()
                .stream()
                .map(productMapper::fromProduct)
                .collect(Collectors.toList());
    }

    @GetMapping("comment")
    public List<ReviewDto> getCommentsByIdProduct(@RequestParam(name = "productId") Integer id) {
        var list = reviewService.getReviewsById(id)
                .stream()
                .map(reviewMapper::fromReview)
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

    @PostMapping("review/save")
    public void saveReview(@RequestBody String id,
                           @AuthenticationPrincipal UserDetails userDetails,
                           @RequestParam(value = "email") String email,
                           @RequestParam(value = "id") String text) {
        String cleanedId = id.replaceAll("[^0-9]", "");
        String[] digits = cleanedId.split("");
        int[] numbers = new int[digits.length];
        for (int i = 0; i < digits.length; i++) {
            numbers[i] = Integer.parseInt(digits[i]);
        }
        for (int num : numbers) {
            String user;
            if (userDetails != null && userDetails.getUsername() != null && !userDetails.getUsername().isEmpty()) {
                user = userDetails.getUsername();
            } else {
                user = null;
            }
            User userId;
            if (user != null) {
                userId = userService.findByEmail(user).get(0);
            } else {
                userId = userService.findByEmail(email).get(0);
            }
            LocalDateTime time = LocalDateTime.now();
            Product product = productService.findProductById(num);
            reviewService.saveReview(userId, text, product, time);
        }
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
