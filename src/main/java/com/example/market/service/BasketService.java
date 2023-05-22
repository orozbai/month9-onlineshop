package com.example.market.service;

import com.example.market.entity.Basket;
import com.example.market.repository.BasketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class BasketService {
    final private BasketRepository basketRepository;

    public void saveBasket(String products, String uni) {
        Basket basket = Basket.builder()
                .products(products)
                .identification(uni)
                .build();
        basketRepository.save(basket);
    }

    public Basket GetLastBasket() {
        return basketRepository.findFirstByOrderByIdDesc();

    }
}
