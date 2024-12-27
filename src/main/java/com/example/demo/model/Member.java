package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Date dateOfBirth;

    // Getters and setters
}
