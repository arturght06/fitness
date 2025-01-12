package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    private final UserRepository userRepository;

    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model) {
        if (authentication != null) {
            // Fetch the authenticated user's details
            String username = authentication.getName();
            var user = userRepository.findByLogin(username)
                    .orElseThrow(() -> new IllegalStateException("User not found"));

            // Map user entity to DTO
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setLogin(user.getLogin());
            userDTO.setRole(user.getRole());

            model.addAttribute("user", userDTO);

            // Redirect based on the user's role
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                String role = authority.getAuthority();
                switch (role) {
                    case "ROLE_ADMIN":
                        return "admin/profile"; // Refers to admin/profile.html in the templates folder
                    case "ROLE_USER":
                        return "user/profile"; // Refers to user/profile.html in the templates folder
                    case "ROLE_ZAWODNIK":
                        return "zawodnik/profile"; // Refers to zawodnik/profile.html in the templates folder
                    case "ROLE_PRACOWNIK":
                        return "pracownik/profile"; // Refers to pracownik/profile.html in the templates folder
                    default:
                        break;
                }
            }
        }
        return "redirect:/error"; // Redirect to an error page if no role is found
    }
}
