package com.example.demo.controller;

import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    private final UserRepository userRepository;

    public PagesController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping({"/", "/home"}) // Mapping for the homepage
    public String home() {
        return "home"; // Refers to home.html in the templates folder
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Refers to login.html in the templates folder
    }

    @GetMapping("/kluby")
    public String klubyPage() {
        return "kluby"; // Refers to kluby.html in the templates folder
    }

    @GetMapping("/wydarzenia")
    public String wydarzenia() {
        return "wydarzenia"; // Refers to wydarzenia.html in the templates folder
    }

//    @GetMapping("/profile")
//    public String getProfile(Authentication authentication, Model model) {
//        String username = authentication.getName(); // Fetch logged-in user's username
//        var user = userRepository.findByLogin(username)
//                .orElseThrow(() -> new IllegalStateException("User not found"));
//
//        // Map user entity to DTO (if required)
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUserId(user.getUserId());
//        userDTO.setLogin(user.getLogin());
//        userDTO.setRole(user.getRole());
//
//        model.addAttribute("user", userDTO);
//        return "profile"; // Refers to profile.html in the templates folder
//    }
}
