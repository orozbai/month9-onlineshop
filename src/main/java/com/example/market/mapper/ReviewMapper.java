package com.example.market.mapper;

import com.example.market.dto.ReviewDto;
import com.example.market.entity.Reviews;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    public ReviewDto fromReview(Reviews reviews) {
        return ReviewDto.builder()
                .name(reviews.getUser().getName())
                .description(reviews.getDescription())
                .descriptionTime(reviews.getDescriptionTime())
                .build();
    }
}
