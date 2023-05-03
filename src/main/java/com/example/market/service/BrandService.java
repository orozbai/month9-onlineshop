package com.example.market.service;

import com.example.market.entity.Brand;
import com.example.market.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandService {
    final private BrandRepository brandRepository;

    public Brand saveBrand(String name) {
        Brand brand = Brand.builder().brand(name).build();

        return brandRepository.save(brand);
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAllBrand();
    }
}
