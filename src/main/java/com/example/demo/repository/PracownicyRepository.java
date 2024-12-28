package com.example.demo.repository;

import com.example.demo.model.Pracownicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PracownicyRepository extends JpaRepository<Pracownicy, Integer> {
}