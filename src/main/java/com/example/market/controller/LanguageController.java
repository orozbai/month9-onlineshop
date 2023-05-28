package com.example.market.controller;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@AllArgsConstructor
@RestController
public class LanguageController {

    @Bean
    public LocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }
}
