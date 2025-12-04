package com.cda.camping.service;

import com.cda.camping.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    // ATTENTION: La clé secrète doit être beaucoup plus longue et complexe en production,
    // et stockée de manière sécurisée (ex: dans les variables d'environnement ou un vault).
    private static final String SECRET_KEY = "une_super_cle_secrete_tres_longue_pour_eviter_les_problemes_de_securite";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 heures

    /**
     * Extrait le login (sujet) du token.
     * @param token Le token JWT.
     * @return Le login de l'utilisateur.
     */
    public String extractLogin(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Génère un token pour un utilisateur.
     * @param user L'utilisateur pour qui générer le token.
     * @return Le token JWT.
     */
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", user.getRoles());
        return createToken(claims, user.getLogin());
    }

    public boolean isTokenValid(String token, User user) {
        final String login = extractLogin(token);
        return (login.equals(user.getLogin())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
