package com.example.market.repository;

import com.example.market.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select u from User as u where u.email like %:email%")
    List<User> findAllUserByEmail(String email);

    @Query(value = "select u from User as u where u.name like %:name%")
    List<User> findAllUserByName(String name);

    @Query(value = "select u from User as u where u.username like %:username%")
    List<User> findAllUserByUsername(String username);

    Optional<User> getByEmail(String email);

    @Query(value = "select u.id from User as u where u.email like %:email%")
    int getUserIdByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.email = :email")
    void updatePassword(String password, String email);
}
