package com.cda.camping.controller;

import com.cda.camping.model.Reservation;
import com.cda.camping.service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        Reservation reservation = reservationService.getReservation(id);
        return new ResponseEntity<>(reservation, headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        List<Reservation> reservations = reservationService.getAllReservations();
        return new ResponseEntity<>(reservations, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody Reservation reservation) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        reservationService.createReservation(reservation);
        return new ResponseEntity<>("{message: 'Réservation ajoutée avec succès'}", headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateReservation(@RequestBody Reservation reservation) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        reservationService.updateReservation(reservation);
        return new ResponseEntity<>("{message: 'Réservation modifiée avec succès'}", headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        reservationService.deleteReservation(id);
        return new ResponseEntity<>("{message: 'Réservation supprimée avec succès'}", headers, HttpStatus.OK);
    }

    @GetMapping("/hebergement/{hebergementId}")
    public ResponseEntity<List<Reservation>> getReservationsByHebergement(@PathVariable Integer hebergementId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        List<Reservation> reservations = reservationService.getReservationsByHebergement(hebergementId);
        return new ResponseEntity<>(reservations, headers, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Reservation> register(@RequestBody Reservation reservation) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        Reservation registeredReservation = reservationService.registerReservation(reservation);
        return new ResponseEntity<>(registeredReservation, headers, HttpStatus.CREATED);
    }
}
