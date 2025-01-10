package com.example.demo.repository;

import com.example.demo.model.RoleUpgradeRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleUpgradeRequestRepository extends JpaRepository<RoleUpgradeRequest, Integer> {
    List<RoleUpgradeRequest> findByStatus(String status);
}
