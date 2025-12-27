package com.ricael. mergemind.config;

import com.ricael.mergemind.domain.User;
import com.ricael.mergemind.repository.UserRepository;
import com.ricael.mergemind. services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework. beans.factory.annotation.Autowired;
import org.springframework. security.authentication.UsernamePasswordAuthenticationToken;
import org. springframework.security.core.context. SecurityContextHolder;
import org. springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {


        String token = extractToken(request);

        if (token != null) {

            String email = tokenService.validateToken(token);

            if (! email.isEmpty()) {

                User user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


                var authentication = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }


        filterChain.doFilter(request, response);
    }


    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }


        return authHeader.substring(7);
    }
}