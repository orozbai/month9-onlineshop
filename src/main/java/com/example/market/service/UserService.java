package com.example.market.service;

import com.example.market.entity.User;
import com.example.market.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    final private UserRepository userRepository;

    public List<User> findBy(Specification<User> spec) {
        return userRepository.findBy(spec);
    }
}
