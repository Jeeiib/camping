package com.cda.camping.controller;

import com.cda.camping.model.Type;
import com.cda.camping.service.TypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/types")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/{id}")
    public ResponseEntity<Type> getTypeById(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        Type type = typeService.getType(id);
        return new ResponseEntity<>(type, headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Type>> getAllTypes() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        List<Type> types = typeService.getAllTypes();
        return new ResponseEntity<>(types, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createType(@RequestBody Type type) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        typeService.createType(type);
        return new ResponseEntity<>("{message: 'Type ajouté avec succès'}", headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateType(@RequestBody Type type) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        typeService.updateType(type);
        return new ResponseEntity<>("{message: 'Type modifié avec succès'}", headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteType(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        typeService.deleteType(id);
        return new ResponseEntity<>("{message: 'Type supprimé avec succès'}", headers, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Type> register(@RequestBody Type type) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        Type registerType = typeService.registerType(type);
        return new ResponseEntity<>(registerType, headers, HttpStatus.CREATED);
    }
}


