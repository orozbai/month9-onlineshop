package com.example.market.service;

import com.example.market.entity.Product;
import com.example.market.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    final private ProductRepository productRepository;
    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }
}
