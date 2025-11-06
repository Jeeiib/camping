package com.cda.camping.controller;

import com.cda.camping.model.Hebergement;
import com.cda.camping.service.HebergementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/hebergements")
public class HebergementController {

    @Autowired
    private HebergementService hebergementService;

    @GetMapping("/{id}")
    public ResponseEntity<Hebergement> getHebergementById(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        Hebergement hebergement = hebergementService.getHebergement(id);
        return new ResponseEntity<>(hebergement, headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Hebergement>> getAllHebergements() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        List<Hebergement> hebergements = hebergementService.getAllHebergements();
        return new ResponseEntity<>(hebergements, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createHebergement(@RequestBody Hebergement hebergement) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        hebergementService.createHebergement(hebergement);
        return new ResponseEntity<>("{message: 'Hébergement ajouté avec succès'}", headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateHebergement(@RequestBody Hebergement hebergement) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        hebergementService.updateHebergement(hebergement);
        return new ResponseEntity<>("{message: 'Hébergement modifié avec succès'}", headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHebergement(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        hebergementService.deleteHebergement(id);
        return new ResponseEntity<>("{message: 'Hébergement supprimé avec succès'}", headers, HttpStatus.OK);
    }

    @GetMapping("/type/{typeId}")
    public ResponseEntity<List<Hebergement>> getHebergementsByType(@PathVariable Integer typeId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        List<Hebergement> hebergements = hebergementService.getHebergementsByType(typeId);
        return new ResponseEntity<>(hebergements, headers, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Hebergement> register(@RequestBody Hebergement hebergement) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        Hebergement registerHebergement = hebergementService.registerHebergement(hebergement);
        return new ResponseEntity<>(registerHebergement, headers, HttpStatus.CREATED);
    }
}
