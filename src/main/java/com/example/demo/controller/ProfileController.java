package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.RoleUpgradeRequest;
import com.example.demo.model.User;
import com.example.demo.repository.RoleUpgradeRequestRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserRepository userRepository;
    private final RoleUpgradeRequestRepository roleUpgradeRequestRepository;

    public ProfileController(UserRepository userRepository, RoleUpgradeRequestRepository roleUpgradeRequestRepository) {
        this.userRepository = userRepository;
        this.roleUpgradeRequestRepository = roleUpgradeRequestRepository;
    }

    @GetMapping
    public String profile(Authentication authentication, Model model,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "5") int size) {
        if (authentication == null) {
            return "redirect:/login";
        }

        String username = authentication.getName();
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        // Map user entity to DTO
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setLogin(user.getLogin());
        userDTO.setRole(user.getRole());

        model.addAttribute("user", userDTO);

        // Add role requests data for admins
        if ("ADMIN".equals(user.getRole())) {
            Pageable pageable = PageRequest.of(page, size);
            Page<RoleUpgradeRequest> requestsPage = roleUpgradeRequestRepository.findByStatus("PENDING", pageable);

            model.addAttribute("requests", requestsPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", requestsPage.getTotalPages());
            model.addAttribute("totalElements", requestsPage.getTotalElements());
            model.addAttribute("pageSize", size);
        }

        return "user/profile";
    }

    @PostMapping("/role-requests/{id}/approve")
    public String approveRequest(@PathVariable Integer id, Principal principal) {
        RoleUpgradeRequest request = roleUpgradeRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));

        // Update the request status and admin information
        request.setStatus("APPROVED");
        request.setReviewDate(LocalDateTime.now());
        User admin = userRepository.findByLogin(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        request.setReviewedBy(admin);

        // Update the user's role
        User user = request.getUser();
        user.setRole(request.getRequestedRole());
        userRepository.save(user);
        roleUpgradeRequestRepository.save(request);

        return "redirect:/profile?success=Approved";
    }

    @PostMapping("/role-requests/{id}/decline")
    public String declineRequest(@PathVariable Integer id, Principal principal) {
        RoleUpgradeRequest request = roleUpgradeRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));

        // Update the request status and admin information
        request.setStatus("DECLINED");
        request.setReviewDate(LocalDateTime.now());
        User admin = userRepository.findByLogin(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        request.setReviewedBy(admin);

        roleUpgradeRequestRepository.save(request);
        return "redirect:/profile?success=Declined";
    }
}
