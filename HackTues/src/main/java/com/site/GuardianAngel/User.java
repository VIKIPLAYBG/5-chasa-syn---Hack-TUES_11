package com.site.HackTues;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "id")
    private Long id;

    @Column(unique = true, nullable = false, name = "name")
    private String name;

    @Column(unique = true, nullable = false, name = "email")
    private String email;
}