package com.example.market.controller;

import com.example.market.dto.UserDto;
import com.example.market.mapper.UserMapper;
import com.example.market.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/user/")
public class UserController {
    final private UserService userService;
    final private UserMapper userMapper;

    //на страрнице сделать выбираемый поиск выпадающий email, name, username
    @GetMapping("find/")
    public List<UserDto> searchUsers(@RequestParam(required = false) String name,
                                     @RequestParam(required = false) String username,
                                     @RequestParam(required = false) String email) {
        List<UserDto> list = new ArrayList<>();
        if (email != null) {
            list = userService.findByEmail(email).stream()
                    .map(user -> userMapper.fromUser(user))
                    .collect(Collectors.toList());
        }
        if (name != null) {
            list = userService.findByName(name).stream()
                    .map(user -> userMapper.fromUser(user))
                    .collect(Collectors.toList());
        }
        if (username != null) {
            list = userService.findByUsername(username).stream()
                    .map(user -> userMapper.fromUser(user))
                    .collect(Collectors.toList());
        }
        return list;
    }

    @GetMapping("check/")
    public Boolean checkUserInSystem(@RequestParam(required = false) String email) {
        List<UserDto> list = new ArrayList<>();
        if (email != null) {
            list = userService.findByEmail(email).stream()
                    .map(user -> userMapper.fromUser(user))
                    .collect(Collectors.toList());
        }
        return list.size() != 0;
    }
}
