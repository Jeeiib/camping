package com.cda.camping.service;

import com.cda.camping.model.Reservation;
import com.cda.camping.repository.ReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation getReservation(Integer id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public void createReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void updateReservation(Reservation reservation) {
        reservationRepository.update(reservation);
    }

    public void deleteReservation(Integer id) {
        reservationRepository.delete(id);
    }

    public List<Reservation> getReservationsByHebergement(Integer hebergementId) {
        return reservationRepository.findByHebergement(hebergementId);
    }

    public Reservation registerReservation(Reservation reservation) {
        reservationRepository.save(reservation);
        return reservation;
    }
}





