package com.example.market.controller;

import com.example.market.dto.BrandCreateDto;
import com.example.market.dto.BrandDto;
import com.example.market.entity.Brand;
import com.example.market.mapper.BrandMapper;
import com.example.market.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class BrandController {
    final private BrandService brandService;
    final private BrandMapper brandMapper;

    @GetMapping("brands/")
    public List<BrandDto> getAllBrands() {
        return brandService.getAllBrands().stream()
                .map(brand -> brandMapper.fromBrand(brand))
                .collect(Collectors.toList());
    }

    @PostMapping("brand/add/")
    public BrandDto addBrand(@RequestBody BrandCreateDto brandCreateDto) {
        Brand brand = brandService.saveBrand(brandCreateDto.getName());
        return brandMapper.fromBrand(brand);
    }
}
