package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping({"/", "/home"}) // Mapowanie na stronę główną
    public String home() {
        return "home"; // Odwołuje się do pliku home.html w folderze templates
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Вернет login.html из папки templates
    }

    @GetMapping("/kluby")
    public String klubyPage() {
        return "kluby"; // Вернет login.html из папки templates
    }

    @GetMapping("/wydarzenia")
    public String wydarzenia() {
        return "wydarzenia"; // Ensure there's a 'wydarzenia.html' template in your templates directory
    }

    @GetMapping("/admin/profile")
    public String adminProfile() {
        return "admin/profile";
    }

    @GetMapping("/user/profile")
    public String userProfile() {
        return "user/profile";
    }

    @GetMapping("/zawodnik/profile")
    public String zawodnikProfile() {
        return "zawodnik/profile";
    }

    @GetMapping("/pracownik/profile")
    public String pracownikProfile() {
        return "pracownik/profile";
    }
}
