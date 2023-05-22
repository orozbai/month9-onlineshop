package com.example.market.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "baskets")
@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String products;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "basket")
    @OrderBy("delivery ASC")
    private List<Order> orders;
    private String identification;
}
