package com.bhanu.service;

import com.bhanu.dto.LoginRequestDto;
import com.bhanu.dto.LoginResponseDto;
import com.bhanu.dto.RegisterRequestDto;
import com.bhanu.entity.Plan;
import com.bhanu.entity.Role;
import com.bhanu.entity.User;
import com.bhanu.repository.UserRepository;
import com.bhanu.util.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final AppUserDetailsService userDetailsService;

    public String register(RegisterRequestDto dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);

        return "User registered successfully";
    }


    public LoginResponseDto login(LoginRequestDto dto) {


        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow();


        String token = jwtUtil.generateToken(user);

        LoginResponseDto response = new LoginResponseDto();

        response.setToken(token);
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setName(user.getName());
        response.setPlan(user.getPlan());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        response.setIsActive(true);
        return response;
    }

    public LoginResponseDto adminLogin(LoginRequestDto dto) {

        if (dto.getEmail().equals("admin@gmail.com") && dto.getPassword().equals("admin")) {

            User admin = new User();
            admin.setId(1111L);
            admin.setEmail("admin@gmail.com");
            admin.setName("Admin");
            admin.setRole(Role.ADMIN);
            admin.setPlan(Plan.PRO);

            String token = jwtUtil.generateToken(admin);

            LoginResponseDto response = new LoginResponseDto();
            response.setToken(token);
            response.setId(admin.getId());
            response.setEmail(admin.getEmail());
            response.setRole(admin.getRole());
            response.setPlan(admin.getPlan());
            response.setName(admin.getName());
            response.setIsActive(true);

            return response;
        }

        throw new RuntimeException("Invalid admin credentials");
    }
}
