package com.example.market.mapper;

import com.example.market.dto.BrandDto;
import com.example.market.entity.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {
    public static BrandDto fromBrand(Brand brand) {
        return BrandDto.builder()
                .name(brand.getBrand())
                .build();
    }
}
