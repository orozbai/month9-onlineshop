package com.example.market.controller;

import com.example.market.dto.OrderDto;
import com.example.market.dto.UserDto;
import com.example.market.dto.UserRegistrationDto;
import com.example.market.mapper.OrderMapper;
import com.example.market.mapper.UserMapper;
import com.example.market.service.OrderService;
import com.example.market.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/user/")
public class UserController {
    final private UserService userService;
    final private UserMapper userMapper;

    final private OrderService orderService;
    final private OrderMapper orderMapper;

    //на страрнице сделать выбираемый поиск выпадающий email, name, username
    @GetMapping("find/")
    public List<UserDto> searchUsers(@RequestParam(required = false) String name,
                                     @RequestParam(required = false) String username,
                                     @RequestParam(required = false) String email) {
        List<UserDto> list = new ArrayList<>();
        if (email != null) {
            list = userService.findByEmail(email)
                    .stream()
                    .map(user -> userMapper.fromUser(user))
                    .collect(Collectors.toList());
        }
        if (name != null) {
            list = userService.findByName(name)
                    .stream()
                    .map(user -> userMapper.fromUser(user))
                    .collect(Collectors.toList());
        }
        if (username != null) {
            list = userService.findByUsername(username)
                    .stream()
                    .map(user -> userMapper.fromUser(user))
                    .collect(Collectors.toList());
        }
        return list;
    }

    @GetMapping("check/")
    public Boolean checkUserInSystem(@RequestParam(required = false) String email) {
        List<UserDto> list = new ArrayList<>();
        if (email != null) {
            list = userService.findByEmail(email)
                    .stream()
                    .map(user -> userMapper.fromUser(user))
                    .collect(Collectors.toList());
        }
        return list.size() != 0;
    }
    //ордера или корзина пользователя по айди
    @GetMapping("order/")
    public List<OrderDto> getOrdersByIdUser(@RequestParam(name = "userId", required = false) Integer id) {
        return orderService.getOrdersByIdUser(id)
                .stream()
                .map(order -> orderMapper.fromOrder(order))
                .collect(Collectors.toList());
    }

    @PostMapping("register/")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDto registrationDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        userService.registerUser(registrationDto);
        return ResponseEntity.ok("User registered successfully");
    }


}
