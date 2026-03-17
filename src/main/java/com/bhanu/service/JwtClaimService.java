//package com.bhanu.service;
//
//import io.jsonwebtoken.Jwt;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JwtClaimService {
//
//    public Long getUserId() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (!(authentication instanceof JwtAuthenticationToken jwtAuth)) {
//            throw new IllegalStateException("Invalid authentication type");
//        }
//
//        Jwt jwt = jwtAuth.getToken();
//
//        Number value = jwt.getClaim("id");
//        if (value == null) {
//            throw new IllegalStateException("userId claim missing");
//        }
//
//        return value.longValue();
//    }
//    public boolean isAdmin(Authentication authentication) {
//        return authentication.getAuthorities()
//                .stream()
//                .anyMatch(authority ->
//                        authority.getAuthority().equals("ROLE_ADMIN"));
//    }
//}
//
