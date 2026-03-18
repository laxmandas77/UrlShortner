package com.bhanu.service;


import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
@Component
public class JwtClaimService {

    public Long getId(Authentication authentication) {

        Object principal = authentication.getPrincipal();

        if (principal instanceof String) {
            throw new IllegalStateException("No ID in principal");
        }

        if (principal instanceof Map<?, ?> claims) {
            return Long.valueOf(claims.get("id").toString());
        }

        throw new IllegalStateException("Invalid authentication principal");
    }

    public boolean isAdmin(Authentication authentication) {
        return authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }
}