package com.example.market.service;

import com.example.market.entity.Product;
import com.example.market.entity.Reviews;
import com.example.market.entity.User;
import com.example.market.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {
    final private ReviewRepository reviewRepository;

    public List<Reviews> getReviewsById(Integer id) {
        return reviewRepository.findReviewsByProductId(id);
    }

    public void saveReview(User userId, String text, Product product, LocalDateTime time) {
        Reviews reviews = Reviews.builder()
                .user(userId)
                .descriptionTime(time)
                .description(text)
                .product(product)
                .build();
        reviewRepository.save(reviews);
    }
}
