package com.cda.camping.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Contrôleur de démonstration
 *
 * Ce contrôleur très simple sert à tester que l'application Spring MVC fonctionne.
 * Il retourne un simple "Hello World" en JSON.
 *
 * @Controller : Indique que cette classe est un contrôleur Spring MVC
 * @RequestMapping : Toutes les routes de ce contrôleur commencent par "/api/demo"
 */
@Controller
@RequestMapping("/api/demo")
public class DemoController {

    /**
     * Route de démonstration qui retourne "Hello World"
     *
     * @GetMapping : Cette méthode répond aux requêtes HTTP GET sur /api/demo
     * @return ResponseEntity contenant "Hello World" avec un statut HTTP 200 (OK)
     *
     * Exemple de requête : GET http://localhost:8080/api/demo
     * Réponse : "Hello World"
     */
    @GetMapping
    public ResponseEntity<String> getDemo() {
        try {
            String data = "Hello World";

            // Configuration des en-têtes HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

            // Retourne la réponse avec les en-têtes et le statut HTTP 200
            return new ResponseEntity<>(data, headers, HttpStatus.OK);
        } catch (Exception e) {
            // En cas d'erreur, retourne un statut HTTP 500
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
