package com.example.demo.controller;

import com.example.demo.model.RoleUpgradeRequest;
import com.example.demo.model.User;
import com.example.demo.repository.RoleUpgradeRequestRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RoleUpgradeRequestRepository roleUpgradeRequestRepository;
    private final UserRepository userRepository;

    public AdminController(RoleUpgradeRequestRepository roleUpgradeRequestRepository, UserRepository userRepository) {
        this.roleUpgradeRequestRepository = roleUpgradeRequestRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/role-requests")
    public String viewRoleRequests(Model model) {
        List<RoleUpgradeRequest> requests = roleUpgradeRequestRepository.findByStatus("PENDING");
        model.addAttribute("requests", requests);
        return "admin/role_requests";
    }

    @PostMapping("/role-requests/{id}/approve")
    public String approveRequest(@PathVariable Integer id, Principal principal) {
        RoleUpgradeRequest request = roleUpgradeRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));

        request.setStatus("APPROVED");
        request.setReviewDate(LocalDateTime.now());
        User admin = userRepository.findByLogin(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        request.setReviewedBy(admin);

        User user = request.getUser();
        user.setRole(request.getRequestedRole());
        userRepository.save(user);
        roleUpgradeRequestRepository.save(request);

        return "redirect:/admin/role-requests?success=Approved";
    }

    @PostMapping("/role-requests/{id}/decline")
    public String declineRequest(@PathVariable Integer id, Principal principal) {
        RoleUpgradeRequest request = roleUpgradeRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));

        request.setStatus("DECLINED");
        request.setReviewDate(LocalDateTime.now());
        User admin = userRepository.findByLogin(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        request.setReviewedBy(admin);

        roleUpgradeRequestRepository.save(request);
        return "redirect:/admin/role-requests?success=Declined";
    }
}
