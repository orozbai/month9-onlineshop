package com.example.market.service;

import com.example.market.dto.UserRegistrationDto;
import com.example.market.entity.User;
import com.example.market.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    final private UserRepository userRepository;

    public List<User> findByEmail(String email) {
        return userRepository.findAllUserByEmail(email);
    }

    public List<User> findByName(String name) {
        return userRepository.findAllUserByName(name);
    }

    public List<User> findByUsername(String username) {
        return userRepository.findAllUserByUsername(username);
    }

    public void registerUser(UserRegistrationDto registrationDto) {
        User user = User.builder()
                .name(registrationDto.getName())
                .email(registrationDto.getEmail())
                .username(registrationDto.getUsername())
                .password(new BCryptPasswordEncoder().encode(registrationDto.getPassword()))
                .enabled(true)
                .role("FULL")
                .build();
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = userRepository.getByEmail(username);
        if (optUser.isEmpty()) {
            throw new UsernameNotFoundException("Not found");
        }
        return optUser.get();
    }

    public void saveGuest(String email) {
        User user = User.builder()
                .name("guest")
                .email(email)
                .username("guest")
                .password("guest")
                .enabled(true)
                .role("FULL")
                .build();
        userRepository.save(user);
    }

    public int getUserIdByEmail(String email) {
        return userRepository.getUserIdByEmail(email);
    }

    @Transactional
    public void updatePassword(String password, String email) {
        userRepository.updatePassword(password, email);
    }
}
