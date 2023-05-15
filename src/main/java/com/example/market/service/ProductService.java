package com.example.market.service;

import com.example.market.entity.Product;
import com.example.market.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    final private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    public Page<Product> findByName(String name, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    public Page<Product> findByNamePage(String name, Pageable pageable) {
        return productRepository.findByName(name, pageable);
    }

    public Page<Product> findByCategory(Long categoryId, Pageable pageable) {
        return productRepository.findByCategory_Id(categoryId, pageable);
    }

    public Page<Product> findByPrice(Integer minPrice, Integer maxPrice, Pageable pageable) {
        return productRepository.findByPriceBetween(minPrice, maxPrice, pageable);
    }

    public Page<Product> getThreeProducts(Pageable pageable) {
        return productRepository.findAllBy(pageable);
    }

    public Page<Product> findByBrandPage(String name, Pageable pageable) {
        return productRepository.findByBrandPage(name, pageable);
    }

    public Page<Product> findByBrandAndDesc(String name, String description, Pageable pageable) {
        return productRepository.findByNameDescription(name, description, pageable);
    }

    public Page<Product> findByDesc(String description, Pageable pageable) {
        return productRepository.findByDesc(description, pageable);
    }
}
