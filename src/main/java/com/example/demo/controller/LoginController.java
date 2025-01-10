package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
class LoginController {
    @GetMapping("/perform_login")
    String performLogin( ) {
        System.out.println("КАКАШКА ГЫЫЫ");
        return "Hello";
    }
}
