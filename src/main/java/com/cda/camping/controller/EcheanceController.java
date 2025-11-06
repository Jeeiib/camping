package com.cda.camping.controller;

import com.cda.camping.model.Echeance;
import com.cda.camping.service.EcheanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/echeances")
public class EcheanceController {

    @Autowired
    private EcheanceService echeanceService;

    @GetMapping("/{id}")
    public ResponseEntity<Echeance> getEcheanceById(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        Echeance echeance = echeanceService.getEcheance(id);
        return new ResponseEntity<>(echeance, headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Echeance>> getAllEcheances() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        List<Echeance> echeances = echeanceService.getAllEcheances();
        return new ResponseEntity<>(echeances, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createEcheance(@RequestBody Echeance echeance) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        echeanceService.createEcheance(echeance);
        return new ResponseEntity<>("{message: 'Échéance ajoutée avec succès'}", headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateEcheance(@RequestBody Echeance echeance) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        echeanceService.updateEcheance(echeance);
        return new ResponseEntity<>("{message: 'Échéance modifiée avec succès'}", headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEcheance(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        echeanceService.deleteEcheance(id);
        return new ResponseEntity<>("{message: 'Échéance supprimée avec succès'}", headers, HttpStatus.OK);
    }

    @GetMapping("/facture/{factureId}")
    public ResponseEntity<List<Echeance>> getEcheancesByFacture(@PathVariable Integer factureId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        List<Echeance> echeances = echeanceService.getEcheancesByFacture(factureId);
        return new ResponseEntity<>(echeances, headers, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Echeance> register(@RequestBody Echeance echeance) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        Echeance registeredEcheance = echeanceService.registerEcheance(echeance);
        return new ResponseEntity<>(registeredEcheance, headers, HttpStatus.CREATED);
    }
}
