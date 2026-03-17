package com.bhanu.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ClickEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    private String city;

    private String device;

    private String browser;

    private String referrer;

    private String ipHash;

    private LocalDateTime clickedAt;

    @ManyToOne
    @JoinColumn(name = "short_link_id")
    private ShortLink shortLink;

}