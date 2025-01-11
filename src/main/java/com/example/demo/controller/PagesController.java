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

    @GetMapping("/admin")
    public String admin() {
        return "admin"; // Ensure there's a 'wydarzenia.html' template in your templates directory
    }

    @GetMapping("/user")
    public String user() {
        return "user"; // Ensure there's a 'wydarzenia.html' template in your templates directory
    }

    @GetMapping("/zawodnik")
    public String zawodnik() {
        return "zawodnik"; // Ensure there's a 'wydarzenia.html' template in your templates directory
    }
}
