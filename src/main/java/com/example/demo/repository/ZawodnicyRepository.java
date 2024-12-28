package com.example.demo.repository;

import com.example.demo.model.Zawodnicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZawodnicyRepository extends JpaRepository<Zawodnicy, Integer> {
}