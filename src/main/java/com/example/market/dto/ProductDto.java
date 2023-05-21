package com.example.market.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class ProductDto {
    private String name;
    private String description;
    private Integer count;
    private Integer price;
    private String brand;
    private String category;
    private String image;
    private Integer id;
}
