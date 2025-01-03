package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping("/") // Mapowanie na stronę główną
    public String home() {
        return "home"; // Odwołuje się do pliku home.html w folderze templates
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Вернет login.html из папки templates
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @GetMapping("/wydarzenia")
    public String wydarzenia() {
        return "wydarzenia"; // Ensure there's a 'wydarzenia.html' template in your templates directory
    }

}
