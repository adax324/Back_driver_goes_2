package com.drivingschool.auth.service;

import com.drivingschool.auth.entity.User;
import com.drivingschool.auth.payload.SignupRequest;
import com.drivingschool.auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(SignupRequest req) {
        if (userRepository.existsByUsername(req.getUsername())) {
            throw new IllegalArgumentException("Username taken");
        }
        User u = new User(req.getUsername(), passwordEncoder.encode(req.getPassword()));
        return userRepository.save(u);
    }
}

