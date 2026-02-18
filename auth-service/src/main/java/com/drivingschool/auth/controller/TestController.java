package com.drivingschool.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/user")
    public String user(@RequestHeader(value = "Authorization", required = false) String auth) {
        return "Hello user. Authorization header: " + auth;
    }
}

