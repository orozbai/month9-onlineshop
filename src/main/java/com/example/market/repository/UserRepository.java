package com.example.market.repository;

import com.example.market.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select u from User as u where u.email like %:email%")
    List<User> findAllUserByEmail(String email);
    @Query(value = "select u from User as u where u.name like %:name%")
    List<User> findAllUserByName(String name);
    @Query(value = "select u from User as u where u.username like %:username%")
    List<User> findAllUserByUsername(String username);
}
