package com.example.market.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderDto {
    private String delivery;
    private Integer price;
    private LocalDateTime orderTime;
    private String userEmail;
    private String productName;
    private Integer productPrice;
}
