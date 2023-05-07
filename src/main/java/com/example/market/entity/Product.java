package com.example.market.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private Integer price;

    private Integer count;

    private String description;

    @Column(name = "image_link")
    private String image;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    @OrderBy("delivery ASC")
    private List<Order> orders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    @OrderBy("descriptionTime ASC")
    private List<Reviews> reviews;
}
