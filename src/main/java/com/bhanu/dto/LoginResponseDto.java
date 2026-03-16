package com.bhanu.dto;

import com.bhanu.entity.Plan;
import com.bhanu.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Data

public class LoginResponseDto

{
    private String token;
    private Long id;
    private String email;
    private String name;
    private Role role;
    private Plan plan;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
