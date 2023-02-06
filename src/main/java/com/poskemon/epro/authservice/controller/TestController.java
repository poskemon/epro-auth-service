package com.poskemon.epro.authservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @PostMapping("/test")
    public String test() {
        return "authService";
    }
}

