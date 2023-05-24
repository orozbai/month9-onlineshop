package com.example.market.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReviewDto {
    private String description;
    private LocalDateTime descriptionTime;
    private String name;
}
