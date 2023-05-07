package com.example.market.controller;

import com.example.market.entity.User;
import com.example.market.service.UserService;
import com.example.market.specification.UserSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user/")
public class UserController {
    final private UserService userService;

    @GetMapping("find/")
    //на страрнице сделать выбираемый поиск выпадающий email name username
    public List<User> searchUsers(@RequestParam(required = false) String name,
                                  @RequestParam(required = false) String username,
                                  @RequestParam(required = false) String email) {

        Specification<User> spec = Specification.where(null);
        if (name != null) {
            spec = spec.and(UserSpecification.hasName(name));
        }
        if (username != null) {
            spec = spec.and(UserSpecification.hasUsername(username));
        }
        if (email != null) {
            spec = spec.and(UserSpecification.hasEmail(email));
        }
        return userService.findBy(spec);
    }
}
