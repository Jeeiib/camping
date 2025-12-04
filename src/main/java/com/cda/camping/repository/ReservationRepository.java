package com.cda.camping.repository;

import com.cda.camping.model.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ReservationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Reservation findById(Integer id) {
        String sql = "SELECT id_resa, date_debut, date_fin, id_hebergement, id_client FROM reservations WHERE id_resa = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new Reservation(rs.getObject("date_debut", LocalDate.class), rs.getObject("date_fin", LocalDate.class), rs.getInt("id_hebergement"), rs.getInt("id_client")));
    }

    public List<Reservation> findAll() {
        String sql = "SELECT id_resa, date_debut, date_fin, id_hebergement, id_client FROM reservations";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Reservation reservation = new Reservation();
            reservation.setId(rs.getInt("id_resa"));
            reservation.setDateDebut(rs.getObject("date_debut", LocalDate.class));
            reservation.setDateFin(rs.getObject("date_fin", LocalDate.class));
            reservation.setIdHebergement(rs.getInt("id_hebergement"));
            reservation.setIdClient(rs.getInt("id_client"));
            return reservation;
        });
    }

    public int save(Reservation reservation) {
        String sql = "INSERT INTO reservations (date_debut, date_fin, id_hebergement, id_client) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, reservation.getDateDebut(), reservation.getDateFin(), reservation.getIdHebergement(), reservation.getIdClient());
    }

    public int update(Reservation reservation) {
        String sql = "UPDATE reservations SET date_debut = ?, date_fin = ?, id_hebergement = ?, id_client = ? WHERE id_resa = ?";
        return jdbcTemplate.update(sql, reservation.getDateDebut(), reservation.getDateFin(), reservation.getIdHebergement(), reservation.getIdClient(), reservation.getId());
    }

    public int delete(Integer id) {
        String sql = "DELETE FROM reservations WHERE id_resa = ?";
        return jdbcTemplate.update(sql, id);
    }

    public List<Reservation> findByHebergement(Integer hebergementId) {
        String sql = "SELECT id_resa, date_debut, date_fin, id_hebergement, id_client FROM reservations WHERE id_hebergement = ?";
        return jdbcTemplate.query(sql, new Object[]{hebergementId}, (rs, rowNum) -> {
            Reservation reservation = new Reservation();
            reservation.setId(rs.getInt("id_resa"));
            reservation.setDateDebut(rs.getObject("date_debut", LocalDate.class));
            reservation.setDateFin(rs.getObject("date_fin", LocalDate.class));
            reservation.setIdHebergement(rs.getInt("id_hebergement"));
            reservation.setIdClient(rs.getInt("id_client"));
            return reservation;
        });
    }
}



