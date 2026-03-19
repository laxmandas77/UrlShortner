package com.bhanu.controller;

import com.bhanu.dto.LoginRequestDto;
import com.bhanu.dto.LoginResponseDto;
import com.bhanu.dto.RegisterRequestDto;
import com.bhanu.dto.UserResponseDto;
import com.bhanu.entity.User;
import com.bhanu.service.AdminService;
import com.bhanu.service.AuthService;

import com.bhanu.service.JwtClaimService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AdminService adminService;
    private final JwtClaimService jwtClaimService;

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
        return ResponseEntity.ok(adminService.adminLogin(dto));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers()
    {

        List<User> list=authService.listAllUsers();
        return ResponseEntity.ok(list);

    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<UserResponseDto> getUserById(
            @PathVariable Long id,
            Authentication authentication) throws AccessDeniedException {

        Long tokenId = jwtClaimService.getId(authentication);
        boolean isAdmin = jwtClaimService.isAdmin(authentication);
        System.out.println("Controller hit: " + id);
        System.out.println("Token ID: " + tokenId);
        if (!isAdmin && !tokenId.equals(id)) {
            throw new AccessDeniedException("You can access only your own data");
        }

        UserResponseDto user = authService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}