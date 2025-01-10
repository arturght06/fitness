package com.example.demo.controller;

import com.example.demo.model.RoleUpgradeRequest;
import com.example.demo.model.User;
import com.example.demo.repository.RoleUpgradeRequestRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RoleUpgradeRequestRepository roleUpgradeRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/role-requests")
    public String viewRoleRequests(Model model) {
        // Fetch all pending requests
        List<RoleUpgradeRequest> requests = roleUpgradeRequestRepository.findByStatus("PENDING");
        model.addAttribute("requests", requests);
        return "admin/role_requests"; // Refers to the admin/role_requests.html template
    }

    @PostMapping("/role-requests/{id}/approve")
    public String approveRequest(@PathVariable Integer id, Principal principal) {
        RoleUpgradeRequest request = roleUpgradeRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));

        // Approve the request
        request.setStatus("APPROVED");
        request.setReviewDate(LocalDateTime.now());

        User admin = userRepository.findByLogin(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        request.setReviewedBy(admin);

        roleUpgradeRequestRepository.save(request);

        // Update the user's role
        User user = request.getUser();
        user.setRole(request.getRequestedRole());
        userRepository.save(user);

        return "redirect:/admin/role-requests?success=Request approved";
    }

    @PostMapping("/role-requests/{id}/decline")
    public String declineRequest(@PathVariable Integer id, Principal principal) {
        RoleUpgradeRequest request = roleUpgradeRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));

        // Decline the request
        request.setStatus("DECLINED");
        request.setReviewDate(LocalDateTime.now());

        User admin = userRepository.findByLogin(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        request.setReviewedBy(admin);

        roleUpgradeRequestRepository.save(request);

        return "redirect:/admin/role-requests?success=Request declined";
    }
}
