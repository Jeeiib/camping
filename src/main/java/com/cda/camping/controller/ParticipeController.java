package com.cda.camping.controller;

import com.cda.camping.model.Participe;
import com.cda.camping.model.ParticipeId;
import com.cda.camping.service.ParticipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/participes")
public class ParticipeController {

    @Autowired
    private ParticipeService participeService;

    @GetMapping("/{idClient}/{idServices}")
    public ResponseEntity<Participe> getParticipeById(@PathVariable Integer idClient, @PathVariable Integer idServices) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        ParticipeId id = new ParticipeId(idClient, idServices);
        Participe participe = participeService.getParticipe(id);
        return new ResponseEntity<>(participe, headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Participe>> getAllParticipes() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        List<Participe> participes = participeService.getAllParticipes();
        return new ResponseEntity<>(participes, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createParticipe(@RequestBody Participe participe) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        participeService.createParticipe(participe);
        return new ResponseEntity<>("{message: 'Participe ajouté avec succès'}", headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateParticipe(@RequestBody Participe participe) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        participeService.updateParticipe(participe);
        return new ResponseEntity<>("{message: 'Participe modifié avec succès'}", headers, HttpStatus.OK);
    }

    @DeleteMapping("/{idClient}/{idServices}")
    public ResponseEntity<String> deleteParticipe(@PathVariable Integer idClient, @PathVariable Integer idServices) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        ParticipeId id = new ParticipeId(idClient, idServices);
        participeService.deleteParticipe(id);
        return new ResponseEntity<>("{message: 'Participe supprimé avec succès'}", headers, HttpStatus.OK);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Participe>> getParticipesByClient(@PathVariable Integer clientId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        List<Participe> participes = participeService.getParticipesByClient(clientId);
        return new ResponseEntity<>(participes, headers, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Participe> register(@RequestBody Participe participe) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        Participe registeredParticipe = participeService.registerParticipe(participe);
        return new ResponseEntity<>(registeredParticipe, headers, HttpStatus.CREATED);
    }
}
