package com.bhanu.dto;

import com.bhanu.entity.Plan;
import com.bhanu.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private Plan plan;
    private Boolean isActive;
}
