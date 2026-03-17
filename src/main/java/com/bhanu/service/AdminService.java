package com.bhanu.service;

import com.bhanu.dto.LoginRequestDto;
import com.bhanu.dto.LoginResponseDto;
import com.bhanu.entity.Plan;
import com.bhanu.entity.Role;
import com.bhanu.entity.User;
import com.bhanu.repository.UserRepository;
import com.bhanu.util.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private static final String ADMIN_EMAIL = "admin@gmail.com";
    private static final String ADMIN_PASSWORD = "admin";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginResponseDto adminLogin(LoginRequestDto dto) {

        if (!ADMIN_EMAIL.equals(dto.getEmail()) || !ADMIN_PASSWORD.equals(dto.getPassword())) {
            throw new RuntimeException("Invalid admin credentials");
        }

        User admin = userRepository.findByEmail(ADMIN_EMAIL)
                .orElseGet(() -> {

                    User newAdmin = new User();
                    newAdmin.setName("Admin");
                    newAdmin.setEmail(ADMIN_EMAIL);
                    newAdmin.setPassword(passwordEncoder.encode(ADMIN_PASSWORD));
                    newAdmin.setRole(Role.ADMIN);
                    newAdmin.setPlan(Plan.PRO);
                    newAdmin.setIsActive(true);

                    return userRepository.save(newAdmin);
                });

        String token = jwtUtil.generateToken(admin);

        LoginResponseDto response = new LoginResponseDto();
        response.setToken(token);
        response.setId(admin.getId());
        response.setEmail(admin.getEmail());
        response.setRole(admin.getRole());
        response.setPlan(admin.getPlan());
        response.setName(admin.getName());
        response.setIsActive(admin.getIsActive());

        return response;
    }
}