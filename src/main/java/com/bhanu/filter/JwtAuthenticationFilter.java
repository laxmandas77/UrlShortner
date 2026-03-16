package com.bhanu.filter;

import com.bhanu.service.AppUserDetailsService;
import com.bhanu.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    private final JwtUtil jwtUtil;
    private final AppUserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String headers=request.getHeader("Authorization");
        if(headers==null || !headers.startsWith("Bearer "))
        {
            filterChain.doFilter(request,response);
            return;
        }
        String token=headers.substring(7);
        try{
            String email = jwtUtil.extractUsername(token);

            if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){

                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                if(jwtUtil.validateToken(token, userDetails)){

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }
        catch (Exception e){
          throw  new RuntimeException("invalid or expired token");
        }
        filterChain.doFilter(request,response);
    }
}
