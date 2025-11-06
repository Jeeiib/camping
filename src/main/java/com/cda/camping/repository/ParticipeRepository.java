package com.cda.camping.repository;

import com.cda.camping.model.Participe;
import com.cda.camping.model.ParticipeId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ParticipeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Participe findById(ParticipeId id) {
        String sql = "SELECT id_client, id_services, date_debut, date_fin FROM participe WHERE id_client = ? AND id_services = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id.getIdClient(), id.getIdServices()}, (rs, rowNum) -> new Participe(rs.getInt("id_client"), rs.getInt("id_services"), rs.getObject("date_debut", LocalDateTime.class), rs.getObject("date_fin", LocalDateTime.class)));
    }

    public List<Participe> findAll() {
        String sql = "SELECT id_client, id_services, date_debut, date_fin FROM participe";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Participe participe = new Participe();
            participe.setIdClient(rs.getInt("id_client"));
            participe.setIdServices(rs.getInt("id_services"));
            participe.setDateDebut(rs.getObject("date_debut", LocalDateTime.class));
            participe.setDateFin(rs.getObject("date_fin", LocalDateTime.class));
            return participe;
        });
    }

    public int save(Participe participe) {
        String sql = "INSERT INTO participe (id_client, id_services, date_debut, date_fin) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, participe.getIdClient(), participe.getIdServices(), Timestamp.valueOf(participe.getDateDebut()), Timestamp.valueOf(participe.getDateFin()));
    }

    public int update(Participe participe) {
        String sql = "UPDATE participe SET date_debut = ?, date_fin = ? WHERE id_client = ? AND id_services = ?";
        return jdbcTemplate.update(sql, Timestamp.valueOf(participe.getDateDebut()), Timestamp.valueOf(participe.getDateFin()), participe.getIdClient(), participe.getIdServices());
    }

    public int delete(ParticipeId id) {
        String sql = "DELETE FROM participe WHERE id_client = ? AND id_services = ?";
        return jdbcTemplate.update(sql, id.getIdClient(), id.getIdServices());
    }

    public List<Participe> findByClient(Integer clientId) {
        String sql = "SELECT id_client, id_services, date_debut, date_fin FROM participe WHERE id_client = ?";
        return jdbcTemplate.query(sql, new Object[]{clientId}, (rs, rowNum) -> {
            Participe participe = new Participe();
            participe.setIdClient(rs.getInt("id_client"));
            participe.setIdServices(rs.getInt("id_services"));
            participe.setDateDebut(rs.getObject("date_debut", LocalDateTime.class));
            participe.setDateFin(rs.getObject("date_fin", LocalDateTime.class));
            return participe;
        });
    }
}



