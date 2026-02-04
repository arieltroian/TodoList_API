package com.arieltroian.todolist.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtFilter extends OncePerRequestFilter {
    private static final String SECRET_KEY = "chave-secreta";

    @Override
    protected void doFilterInternal(@org.jetbrains.annotations.NotNull HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws java.io.IOException, jakarta.servlet.ServletException {
        String authHeader = request.getHeader("Autorização");

        if(authHeader != null && authHeader.startsWith("Bearer")){
            String token = authHeader.substring(7);

            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            request.setAttribute("userId", Long.parseLong(claims.getSubject()));
        }

        filterChain.doFilter(request, response);
    }
}
