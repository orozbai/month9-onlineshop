package com.example.market.service;

import com.example.market.entity.Product;
import com.example.market.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Page<Product> findByMaxMinNameDesc(String name, String description, Integer min, Integer max, Pageable pageable) {
        return productRepository.findByMinMaxNameDesc(name, description, min, max, pageable);
    }

    public Page<Product> findByBetween(Integer max, Integer min, Pageable pageable) {
        return productRepository.findByPriceBetweenPage(min, max, pageable);
    }

    public Page<Product> findByNameBetween(String name, Integer min, Integer max, Pageable pageable) {
        return productRepository.findByNameAndBetween(name, min, max, pageable);
    }

    public Page<Product> findByDescBetween(String description, Integer min, Integer max, Pageable pageable) {
        return productRepository.findByDescBetween(description, min, max, pageable);
    }

    public Optional<Product> findById(int id) {
        return productRepository.getProductById(id);
    }

    public Product findProductById(int num) {
        return productRepository.findProductById(num);
    }
}
