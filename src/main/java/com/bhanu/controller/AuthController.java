package com.bhanu.controller;

import com.bhanu.dto.LoginRequestDto;
import com.bhanu.dto.LoginResponseDto;
import com.bhanu.dto.RegisterRequestDto;
import com.bhanu.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterRequestDto dto)
    {
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto dto)
    {
        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping("/admin/login")
    public ResponseEntity<LoginResponseDto> adminLogin(
             @RequestBody LoginRequestDto dto)
    {
        return ResponseEntity.ok(authService.adminLogin(dto));
    }
}