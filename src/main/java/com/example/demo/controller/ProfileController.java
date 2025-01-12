package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profile(Authentication authentication) {
        if (authentication != null) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                String role = authority.getAuthority();
                switch (role) {
                    case "ROLE_ADMIN":
                        return "redirect:/admin/profile";
                    case "ROLE_USER":
                        return "redirect:/user/profile";
                    case "ROLE_ZAWODNIK":
                        return "redirect:/zawodnik/profile";
                    case "ROLE_PRACOWNIK":
                        return "redirect:/pracownik/profile";
                    default:
                        break;
                }
            }
        }
        return "redirect:/error"; // Перенаправление на страницу ошибки, если роль не найдена
    }
}
