package com.example.demo.controller;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
class LoginController {
    @Autowired
    private UserController userController;

    @PostMapping("/perform_login")
    String performLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            RedirectAttributes redirectAttributes) {
        ResponseEntity<Boolean> response = userController.findByLoginAndCheckPassword(username, password);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody()) {
            redirectAttributes.addFlashAttribute("success", "Login successful!");
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login";
        }
    }

}
