package com.example.market.mapper;

import com.example.market.dto.UserDto;
import com.example.market.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto fromUser(User user) {
        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }
}
