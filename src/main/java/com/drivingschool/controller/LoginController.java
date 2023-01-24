//package com.drivingschool.controller;
//
//import com.drivingschool.entity.ERole;
//import com.drivingschool.entity.Role;
//import com.drivingschool.entity.User;
//import com.drivingschool.payload.request.LoginRequest;
//import com.drivingschool.payload.request.SignupRequest;
//import com.drivingschool.payload.response.MessageResponse;
//import com.drivingschool.payload.response.UserInfoResponse;
//import com.drivingschool.repository.RoleRepo;
//import com.drivingschool.repository.UserRepo;
//import com.drivingschool.service.UserDetailsImpl;
//import com.drivingschool.utils.JwtUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseCookie;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping(path = "/api/auth")
//public class LoginController {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    UserRepo userRepository;
//    @Autowired
//    RoleRepo roleRepository;
//    @Autowired
//    PasswordEncoder passwordEncoder;
//    @Autowired
//    JwtUtils jwtUtils;
//
//    @PostMapping("/signin")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());
//        return ResponseEntity.ok()
//                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
//                .body(new UserInfoResponse(
//                        userDetails.getId(),
//                        userDetails.getUsername(),
//                        userDetails.getEmail(),
//                        roles
//                ));
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
//        if (userRepository.existsByUsername(signupRequest.getUsername()))
//            return ResponseEntity.badRequest().body(new MessageResponse("Error: username taken"));
//        if (userRepository.existsByEmail(signupRequest.getEmail()))
//            return ResponseEntity.badRequest().body(new MessageResponse("Error: email taken"));
//
//        User user = new User(signupRequest.getUsername(),
//                signupRequest.getEmail(),
//                passwordEncoder.encode(signupRequest.getPassword()));
//        Set<String> strRoles = signupRequest.getRole();
//        Set<Role> roles = new HashSet<>();
//
//        if (strRoles == null)
//            roles.add(roleRepository.findByName(ERole.ROLE_USER));
//        else {
//            strRoles.forEach(item -> {
//                switch (item) {
//                    case "admin":
//                        roles.add(roleRepository.findByName(ERole.ROLE_ADMIN));
//                        break;
//                    case "mod":
//                        roles.add(roleRepository.findByName(ERole.ROLE_MODERATOR));
//                        break;
//                    default:
//                        roles.add(roleRepository.findByName(ERole.ROLE_USER));
//                }
//            });
//        }
//        user.setRoles(roles);
//        userRepository.save(user);
//        return ResponseEntity.ok(new MessageResponse("Register success"));
//    }
//
//    @PostMapping("/signout")
//    public ResponseEntity<?> logoutUser() {
//        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
//        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
//                .body(new MessageResponse("You've been signed out!"));
//    }
//
//}
