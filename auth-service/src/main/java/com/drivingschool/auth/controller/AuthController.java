package com.drivingschool.auth.controller;

import com.drivingschool.auth.entity.User;
import com.drivingschool.auth.payload.JwtResponse;
import com.drivingschool.auth.payload.LoginRequest;
import com.drivingschool.auth.payload.SignupRequest;
import com.drivingschool.auth.repository.UserRepository;
import com.drivingschool.auth.security.JwtUtils;
import com.drivingschool.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, UserRepository userRepository, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        try {
            User u = userService.register(signUpRequest);
            return ResponseEntity.ok("User registered");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return userRepository.findByUsername(loginRequest.getUsername())
                .map(user -> {
                    if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                        String token = jwtUtils.generateJwtToken(user.getUsername());
                        return ResponseEntity.ok(new JwtResponse(token, user.getUsername()));
                    }
                    return ResponseEntity.status(401).body("Invalid credentials");
                }).orElseGet(() -> ResponseEntity.status(401).body("Invalid credentials"));
    }
}

