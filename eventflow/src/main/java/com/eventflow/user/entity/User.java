package com.eventflow.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, name = "password_hash")
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false , length = 20)
    private Role role;

    @Column(length = 20)
    private String phone;

    @Column(nullable = false)
    private LocalDateTime createdAt; //2007-12-03T10:15:30

    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
