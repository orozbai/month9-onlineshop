package com.example.market.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String username;
    private String email;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OrderBy("delivery ASC")
    private List<Order> orders;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OrderBy("descriptionTime ASC")
    private List<Reviews> reviews;
}
