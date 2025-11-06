package com.cda.camping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cda.camping.model.Client;
import com.cda.camping.service.ClientService;

/**
 * Contrôleur REST pour la gestion des clients
 *
 * Ce contrôleur expose une API REST pour effectuer des opérations CRUD sur les clients.
 * Toutes les méthodes retournent du JSON et suivent les conventions RESTful.
 *
 * @Controller : Indique que cette classe est un contrôleur Spring MVC
 * @RequestMapping : Définit le préfixe d'URL "/api/clients" pour toutes les routes de ce contrôleur
 *
 * ROUTES DISPONIBLES :
 * - GET    /api/clients     -> Récupère tous les clients
 * - GET    /api/clients/{id} -> Récupère un client par son ID
 * - POST   /api/clients     -> Crée un nouveau client
 * - PUT    /api/clients     -> Met à jour un client existant
 * - DELETE /api/clients/{id} -> Supprime un client
 */
@Controller
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    /**
     * Injection automatique du service
     * @Autowired : Spring injecte automatiquement l'instance de ClientService
     */
    @Autowired
    private ClientService clientService;

    /**
     * Récupère tous les clients
     *
     * @GetMapping : Indique que cette méthode répond aux requêtes HTTP GET sur /api/clients
     * @return ResponseEntity contenant la liste des clients et un code HTTP 200 (OK)
     *
     * Exemple de requête : GET http://localhost:8080/api/clients
     */
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        try {
            // Configuration des en-têtes HTTP pour indiquer que la réponse est en JSON
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

            // Retourne la liste des clients avec un statut HTTP 200 (OK)
            return new ResponseEntity<>(clientService.getAllClients(), headers, HttpStatus.OK);
        } catch (Exception e) {
            // En cas d'erreur, retourne un statut HTTP 500 (Erreur serveur)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Récupère un client par son identifiant
     *
     * @PathVariable : Récupère la valeur {id} depuis l'URL
     * @param id L'identifiant du client à rechercher
     * @return ResponseEntity contenant le client trouvé et un code HTTP 200 (OK)
     *
     * Exemple de requête : GET http://localhost:8080/api/clients/5
     */
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Integer id) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(clientService.getClient(id), headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Crée un nouveau client
     *
     * @PostMapping : Indique que cette méthode répond aux requêtes HTTP POST
     * @RequestBody : Convertit automatiquement le JSON reçu en objet Client
     * @param client L'objet Client à créer (reçu depuis le corps de la requête en JSON)
     * @return ResponseEntity avec un message de succès et un code HTTP 201 (Created)
     *
     * Exemple de requête : POST http://localhost:8080/api/clients
     * Body (JSON) :
     * {
     *   "nom": "Dupont",
     *   "prenom": "Jean",
     *   "adresse": "123 rue de Paris",
     *   "telephone": "0612345678",
     *   "idUser": 1
     * }
     */
    @PostMapping
    public ResponseEntity<String> createClient(@RequestBody Client client) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
            clientService.createClient(client);
            // Retourne un statut HTTP 201 (Created) pour indiquer que la ressource a été créée
            return new ResponseEntity<>("{message: 'Client ajouté avec succès'}",headers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Met à jour un client existant
     *
     * @PutMapping : Indique que cette méthode répond aux requêtes HTTP PUT
     * @RequestBody : Convertit le JSON reçu en objet Client
     * @param client L'objet Client avec les nouvelles données (doit contenir l'id)
     * @return ResponseEntity avec un message de succès et un code HTTP 201
     *
     * Exemple de requête : PUT http://localhost:8080/api/clients
     * Body (JSON) :
     * {
     *   "id": 5,
     *   "nom": "Dupont",
     *   "prenom": "Jean",
     *   "adresse": "456 avenue de Lyon",
     *   "telephone": "0698765432",
     *   "idUser": 1
     * }
     */
    @PutMapping
    public ResponseEntity<String> updateClient(@RequestBody Client client) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
            clientService.updateClient(client);
            return new ResponseEntity<>("{message: 'Client modifié avec succès'}",headers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Supprime un client
     *
     * @DeleteMapping : Indique que cette méthode répond aux requêtes HTTP DELETE
     * @PathVariable : Récupère la valeur {id} depuis l'URL
     * @param id L'identifiant du client à supprimer
     * @return ResponseEntity avec un message de succès et un code HTTP 200 (OK)
     *
     * Exemple de requête : DELETE http://localhost:8080/api/clients/5
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Integer id) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
            clientService.deleteClient(id);
            return new ResponseEntity<>("{message: 'Client supprimé avec succès'}",headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}   

