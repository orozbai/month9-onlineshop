package com.example.market.service;

import com.example.market.entity.Reviews;
import com.example.market.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {
    final private ReviewRepository reviewRepository;

    public List<Reviews> getReviewsById(Integer id) {
        return reviewRepository.findReviewsByProductId(id);
    }
}
