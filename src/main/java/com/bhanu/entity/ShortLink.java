package com.bhanu.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ShortLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalUrl;

    @Column(unique = true)
    private String shortCode;

    private String customAlias;

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    private Long clickCount = 0L;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
