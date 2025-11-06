package com.cda.camping.repository;

import com.cda.camping.model.Hebergement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HebergementRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Hebergement findById(Integer id) {
        String sql = "SELECT id_hebergement, emplacement, etat, id_type FROM hebergements WHERE id_hebergement = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new Hebergement(rs.getString("emplacement"), rs.getString("etat"), rs.getInt("id_type")));
    }

    public List<Hebergement> findAll() {
        String sql = "SELECT id_hebergement, emplacement, etat, id_type FROM hebergements";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Hebergement hebergement = new Hebergement();
            hebergement.setId(rs.getInt("id_hebergement"));
            hebergement.setEmplacement(rs.getString("emplacement"));
            hebergement.setEtat(rs.getString("etat"));
            hebergement.setIdType(rs.getInt("id_type"));
            return hebergement;
        });
    }

    public int save(Hebergement hebergement) {
        String sql = "INSERT INTO hebergements (emplacement, etat, id_type) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, hebergement.getEmplacement(), hebergement.getEtat(), hebergement.getIdType());
    }

    public int update(Hebergement hebergement) {
        String sql = "UPDATE hebergements SET emplacement = ?, etat = ?, id_type = ? WHERE id_hebergement = ?";
        return jdbcTemplate.update(sql, hebergement.getEmplacement(), hebergement.getEtat(), hebergement.getIdType(), hebergement.getId());
    }

    public int delete(Integer id) {
        String sql = "DELETE FROM hebergements WHERE id_hebergement = ?";
        return jdbcTemplate.update(sql, id);
    }

    public List<Hebergement> findByType(Integer typeId) {
        String sql = "SELECT id_hebergement, emplacement, etat, id_type FROM hebergements WHERE id_type = ?";
        return jdbcTemplate.query(sql, new Object[]{typeId}, (rs, rowNum) -> {
            Hebergement hebergement = new Hebergement();
            hebergement.setId(rs.getInt("id_hebergement"));
            hebergement.setEmplacement(rs.getString("emplacement"));
            hebergement.setEtat(rs.getString("etat"));
            hebergement.setIdType(rs.getInt("id_type"));
            return hebergement;
        });
    }
}






