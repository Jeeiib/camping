package com.cda.camping.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            final String header = request.getHeader("Authorization");

            String username = null;
            String token = null;

            // Si un token Bearer est présent, on tente de l'authentifier
            if (header != null && header.startsWith("Bearer ")) {
                token = header.substring(7);
                if (JwtUtil.validateToken(token)) {
                    username = JwtUtil.extractUsername(token);
                    // Authentification réussie
                    SecurityContextHolder.getContext()
                            .setAuthentication(new UsernamePasswordAuthenticationToken(username, null, null));
                }
            }
            // On laisse passer la requête - Spring Security décidera si elle est autorisée ou non
            // (les endpoints permitAll() passeront, les autres seront bloqués par Spring Security)
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
