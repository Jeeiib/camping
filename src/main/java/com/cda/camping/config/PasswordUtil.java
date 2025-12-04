package com.cda.camping.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtil {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Encode un mot de passe en clair avec BCrypt
     * @param rawPassword le mot de passe en clair
     * @return le mot de passe hashé
     */
    public static String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * Vérifie si un mot de passe en clair correspond au hash
     * @param rawPassword le mot de passe en clair
     * @param encodedPassword le mot de passe hashé
     * @return true si les mots de passe correspondent
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
