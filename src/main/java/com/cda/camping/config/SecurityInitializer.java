package com.cda.camping.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Initialise Spring Security dans l'application web
 *
 * Cette classe est OBLIGATOIRE pour activer Spring Security dans une application
 * Spring MVC traditionnelle (sans Spring Boot).
 *
 * Elle enregistre automatiquement le DelegatingFilterProxy qui intercepte
 * toutes les requêtes HTTP et les route vers la chaîne de filtres Spring Security.
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    // Pas besoin de code - l'héritage suffit
}
