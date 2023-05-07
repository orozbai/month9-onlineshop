package com.example.market.specification;

import com.example.market.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> hasName(String name) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%"));
    }

    public static Specification<User> hasUsername(String username) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("username"), "%" + username + "%"));
    }

    public static Specification<User> hasEmail(String email) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("email"), "%" + email + "%"));
    }
}
