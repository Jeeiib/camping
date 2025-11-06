package com.cda.camping.controller;

import com.cda.camping.model.FactureService;
import com.cda.camping.service.FactureServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/factures_services")
public class FactureServiceController {

    @Autowired
    private FactureServiceService factureServiceService;

    @GetMapping("/{id}")
    public ResponseEntity<FactureService> getFactureServiceById(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        FactureService factureService = factureServiceService.getFactureService(id);
        return new ResponseEntity<>(factureService, headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FactureService>> getAllFactureServices() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        List<FactureService> factureServices = factureServiceService.getAllFactureServices();
        return new ResponseEntity<>(factureServices, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createFactureService(@RequestBody FactureService factureService) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        factureServiceService.createFactureService(factureService);
        return new ResponseEntity<>("{message: 'FactureService ajoutée avec succès'}", headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateFactureService(@RequestBody FactureService factureService) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        factureServiceService.updateFactureService(factureService);
        return new ResponseEntity<>("{message: 'FactureService modifiée avec succès'}", headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFactureService(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        factureServiceService.deleteFactureService(id);
        return new ResponseEntity<>("{message: 'FactureService supprimée avec succès'}", headers, HttpStatus.OK);
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<FactureService> getFactureServiceByServiceId(@PathVariable Integer serviceId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        FactureService factureService = factureServiceService.getFactureServiceByServiceId(serviceId);
        return new ResponseEntity<>(factureService, headers, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<FactureService> register(@RequestBody FactureService factureService) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        FactureService registeredFactureService = factureServiceService.registerFactureService(factureService);
        return new ResponseEntity<>(registeredFactureService, headers, HttpStatus.CREATED);
    }
}
