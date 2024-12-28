package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // Mapowanie na stronę główną
    public String home() {
        return "home"; // Odwołuje się do pliku home.html w folderze templates
    }
}
