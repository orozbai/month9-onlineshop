package com.example.market.controller;

import com.example.market.dto.OrderDto;
import com.example.market.dto.UserDto;
import com.example.market.entity.Basket;
import com.example.market.entity.User;
import com.example.market.mapper.OrderMapper;
import com.example.market.mapper.UserMapper;
import com.example.market.service.BasketService;
import com.example.market.service.OrderService;
import com.example.market.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@ControllerAdvice
@RequestMapping("/user/")
public class UserController {
    final private UserService userService;
    final private UserMapper userMapper;
    final private BasketService basketService;

    final private OrderService orderService;
    final private OrderMapper orderMapper;

    @GetMapping("find")
    public List<UserDto> searchUsers(@RequestParam(required = false) String name,
                                     @RequestParam(required = false) String username,
                                     @RequestParam(required = false) String email) {
        List<UserDto> list = new ArrayList<>();
        if (email != null) {
            list = userService.findByEmail(email)
                    .stream()
                    .map(userMapper::fromUser)
                    .collect(Collectors.toList());
        }
        if (name != null) {
            list = userService.findByName(name)
                    .stream()
                    .map(userMapper::fromUser)
                    .collect(Collectors.toList());
        }
        if (username != null) {
            list = userService.findByUsername(username)
                    .stream()
                    .map(userMapper::fromUser)
                    .collect(Collectors.toList());
        }
        return list;
    }

    @GetMapping("check")
    public Boolean checkUserInSystem(@RequestParam(required = false) String email) {
        List<UserDto> list = new ArrayList<>();
        if (email != null) {
            list = userService.findByEmail(email)
                    .stream()
                    .map(userMapper::fromUser)
                    .collect(Collectors.toList());
        }
        return list.size() != 0;
    }

    @GetMapping("order")
    public List<OrderDto> getOrdersByIdUser(@RequestParam(name = "userId", required = false) Integer id) {
        return orderService.getOrdersByIdUser(id)
                .stream()
                .map(orderMapper::fromOrder)
                .collect(Collectors.toList());
    }

    @GetMapping("current-user")
    public ResponseEntity<String> getUser(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null && userDetails.getUsername() != null && !userDetails.getUsername().isEmpty()) {
            return ResponseEntity.ok("{\"user\":\"" + userDetails.getUsername() + "\"}");
        } else {
            return ResponseEntity.ok("{\"error\":\"Пользователь не авторизован\"}");
        }
    }

    @PostMapping("delivery")
    public ResponseEntity<?> addGuestUserAndDelivery(@RequestParam(value = "totalPrice") int totalPrice,
                                                     @RequestParam(value = "address") String address,
                                                     @RequestParam(value = "email") String email,
                                                     @RequestParam(value = "uni") String uni,
                                                     @RequestBody String products,
                                                     @AuthenticationPrincipal UserDetails userDetails) {
        String user;
        if (userDetails != null && userDetails.getUsername() != null && !userDetails.getUsername().isEmpty()) {
            user = userDetails.getUsername();
        } else {
            user = null;
        }
        if (user != null) {
            userService.saveGuest(user);
        } else {
            userService.saveGuest(email);
        }
        basketService.saveBasket(products, uni);
        User userId = userService.findByEmail(email).get(0);
        Basket basket = basketService.GetLastBasket();
        orderService.saveOrder(totalPrice, address, userId, basket);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("cart/find")
    public String getListCart(@RequestParam(value = "uni") String uni) {
        return basketService.findBasket(uni);
    }

    @PostMapping("change/password")
    public void changePassword(@RequestBody String password,
                               @RequestParam(value = "email") String email) {
        String encodePsw = new BCryptPasswordEncoder().encode(password);
        userService.updatePassword(encodePsw, email);
    }
}
