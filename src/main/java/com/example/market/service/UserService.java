package com.example.market.service;

import com.example.market.entity.User;
import com.example.market.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    final private UserRepository userRepository;

    public List<User> findByEmail(String email) {
        return userRepository.findAllUserByEmail(email);
    }

    public List<User> findByName(String name) {
        return userRepository.findAllUserByName(name);
    }

    public List<User> findByUsername(String username) {
        return userRepository.findAllUserByUsername(username);
    }
}
