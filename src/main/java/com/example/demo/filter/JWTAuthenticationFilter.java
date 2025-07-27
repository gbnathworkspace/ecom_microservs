package com.example.demo.filter;

import com.example.demo.Utils.JWTUtil;
import io.jsonwebtoken.Claims;
import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Collections;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;

    public JWTAuthenticationFilter(JWTUtil jwtUtil)
    {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        System.out.println("JWT Filter executing for: " + request.getRequestURI());
        return path.startsWith("/auth/") || path.equals("/dbhealth");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, java.io.IOException {
        String authHeader = request.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer "))
        {
            String token = authHeader.substring(7);
             Claims claims = jwtUtil.validateToken(token);

             if(claims != null)
             {
                 String userId = claims.get("userId").toString();
                 String emailId = claims.get("email").toString();

                 UsernamePasswordAuthenticationToken authentication =
                         new UsernamePasswordAuthenticationToken(
                                 emailId,
                                 null,
                                 Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                         );
                 SecurityContextHolder.getContext().setAuthentication(authentication);
             }
        }
        filterChain.doFilter(request, response);
    }

}
