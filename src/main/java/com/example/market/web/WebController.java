package com.example.market.web;

import com.example.market.dto.UserRegistrationDto;
import com.example.market.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class WebController {

    final private UserService userService;
    final private MessageSource messageSource;

    @GetMapping("products")
    public String getProductWithName() {
        return "page";
    }

    @GetMapping("logout")
    public String logoutGet() {
        SecurityContextHolder.clearContext();
        return "redirect:/login";
    }

    @PostMapping("logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "redirect:/login";
    }

    @GetMapping("forgotten")
    public String forgottenPsw() {
        return "forgotten";
    }

    @GetMapping("/error/405")
    public String handle405Error() {
        return "shopcopy";
    }

    @GetMapping()
    public String mainPage() {
        return "shopcopy";
    }

    @GetMapping("register")
    public String register() {
        return "register";
    }

    @RequestMapping("login")
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return "login";
    }

    @PostMapping("register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDto registrationDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        userService.registerUser(registrationDto);
        return ResponseEntity.ok("Вы успешно зарегестрировались!");
    }

    @GetMapping("orders")
    public String getOrders() {
        return "orders";
    }

    @PostMapping("orders/add")
    public void addOrders() {

    }
}
