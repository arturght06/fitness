package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
class LoginController {
    @PostMapping("/perform_login")
    String performLogin(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        System.out.println(username);
        System.out.println(password);
        if (!"skibidi".equals(username) || !"password123".equals(password)) {
            model.addAttribute("error", "Invalid username or password.");
            return "login"; // Возвращаем обратно на страницу логина с ошибкой
        }
        return "login";
    }
}
