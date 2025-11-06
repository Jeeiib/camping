package com.cda.camping.controller;

import com.cda.camping.model.Services;
import com.cda.camping.service.ServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/{id}")
    public ResponseEntity<Services> getServiceById(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        Services service = serviceService.getService(id);
        return new ResponseEntity<>(service, headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Services>> getAllServices() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        List<Services> services = serviceService.getAllServices();
        return new ResponseEntity<>(services, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createService(@RequestBody Services service) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        serviceService.createService(service);
        return new ResponseEntity<>("{message: 'Service ajouté avec succès'}", headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateService(@RequestBody Services service) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        serviceService.updateService(service);
        return new ResponseEntity<>("{message: 'Service modifié avec succès'}", headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteService(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        serviceService.deleteService(id);
        return new ResponseEntity<>("{message: 'Service supprimé avec succès'}", headers, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Services> register(@RequestBody Services service) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        Services registeredService = serviceService.registerService(service);
        return new ResponseEntity<>(registeredService, headers, HttpStatus.CREATED);
    }
}



