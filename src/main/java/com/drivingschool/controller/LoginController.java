package com.drivingschool.controller;

import com.drivingschool.entity.ERole;
import com.drivingschool.entity.Role;
import com.drivingschool.entity.User;
import com.drivingschool.payload.request.LoginRequest;
import com.drivingschool.payload.request.SignupRequest;
import com.drivingschool.payload.response.MessageResponse;
import com.drivingschool.payload.response.UserInfoResponse;
import com.drivingschool.repository.RoleRepo;
import com.drivingschool.repository.UserRepo;
import com.drivingschool.service.LoginService;
import com.drivingschool.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private RoleRepo roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private LoginService loginService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody(required = false) LoginRequest loginRequest, @CookieValue(name = "authorizationToken", required = false) String token) {
        ResponseCookie cookieToken = null;
        if (loginRequest != null) {
            cookieToken = loginService.authenticateWithCredentials(loginRequest.getUsername(), loginRequest.getPassword());
        } else if (token != null) {
            cookieToken = loginService.authenticateWithToken(token);
        }
        if (cookieToken != null) {
            User user = userRepository.findByUsername(jwtUtil.getUsernameFromJwtToken(cookieToken.getValue()));
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, cookieToken.toString())
                    .body(new UserInfoResponse(
                            user.getId(),
                            user.getUsername(),
                            user.getEmail(),
                            user.getRoles().stream()
                                    .map(item -> item.getName().name())
                                    .collect(Collectors.toList())
                    ));
        } else
            return ResponseEntity.badRequest().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername()))
            return ResponseEntity.badRequest().body(new MessageResponse("Error: username taken"));
        if (userRepository.existsByEmail(signupRequest.getEmail()))
            return ResponseEntity.badRequest().body(new MessageResponse("Error: email taken"));

        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));
        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null)
            roles.add(roleRepository.findByName(ERole.ROLE_USER));
        else {
            strRoles.forEach(item -> {
                switch (item) {
                    case "admin":
                        roles.add(roleRepository.findByName(ERole.ROLE_ADMIN));
                        break;
                    case "mod":
                        roles.add(roleRepository.findByName(ERole.ROLE_MODERATOR));
                        break;
                    default:
                        roles.add(roleRepository.findByName(ERole.ROLE_USER));
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("Register success"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtil.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }

}
