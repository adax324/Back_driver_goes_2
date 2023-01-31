package com.drivingschool.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController

public class UserController {
    @GetMapping("/user")
    public Principal user(Principal principal, HttpServletRequest request, @RequestHeader HttpHeaders headers) {
        return principal;
    }
}
