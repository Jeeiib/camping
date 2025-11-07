package com.cda.camping.controller;

import com.cda.camping.model.Facture;
import com.cda.camping.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/factures")
public class FactureController {

    @Autowired
    private FactureService factureService;

    @GetMapping("/{id}")
    public ResponseEntity<Facture> getFactureById(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        Facture facture = factureService.getFacture(id);
        return new ResponseEntity<>(facture, headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Facture>> getAllFactures() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        List<Facture> factures = factureService.getAllFactures();
        return new ResponseEntity<>(factures, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createFacture(@RequestBody Facture facture) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        factureService.createFacture(facture);
        return new ResponseEntity<>("{\"message\": \"Facture ajoutée avec succès\"}", headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateFacture(@RequestBody Facture facture) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        factureService.updateFacture(facture);
        return new ResponseEntity<>("{\"message\": \"Facture modifiée avec succès\"}", headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFacture(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        factureService.deleteFacture(id);
        return new ResponseEntity<>("{\"message\": \"Facture supprimée avec succès\"}", headers, HttpStatus.OK);
    }

    @GetMapping("/reservation/{resaId}")
    public ResponseEntity<Facture> getFactureByReservationId(@PathVariable Integer resaId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        Facture facture = factureService.getFactureByReservationId(resaId);
        return new ResponseEntity<>(facture, headers, HttpStatus.OK);
    }
}
