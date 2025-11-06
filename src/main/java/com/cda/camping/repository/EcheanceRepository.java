package com.cda.camping.repository;

import com.cda.camping.model.Echeance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public class EcheanceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Echeance findById(Integer id) {
        String sql = "SELECT id_echeance, date_echeance, montant, payer, id_facture FROM echeances WHERE id_echeance = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new Echeance(rs.getObject("date_echeance", LocalDate.class), rs.getBigDecimal("montant"), rs.getBoolean("payer"), rs.getInt("id_facture")));
    }

    public List<Echeance> findAll() {
        String sql = "SELECT id_echeance, date_echeance, montant, payer, id_facture FROM echeances";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Echeance echeance = new Echeance();
            echeance.setId(rs.getInt("id_echeance"));
            echeance.setDateEcheance(rs.getObject("date_echeance", LocalDate.class));
            echeance.setMontant(rs.getBigDecimal("montant"));
            echeance.setPayer(rs.getBoolean("payer"));
            echeance.setIdFacture(rs.getInt("id_facture"));
            return echeance;
        });
    }

    public int save(Echeance echeance) {
        String sql = "INSERT INTO echeances (date_echeance, montant, payer, id_facture) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, echeance.getDateEcheance(), echeance.getMontant(), echeance.getPayer(), echeance.getIdFacture());
    }

    public int update(Echeance echeance) {
        String sql = "UPDATE echeances SET date_echeance = ?, montant = ?, payer = ?, id_facture = ? WHERE id_echeance = ?";
        return jdbcTemplate.update(sql, echeance.getDateEcheance(), echeance.getMontant(), echeance.getPayer(), echeance.getIdFacture(), echeance.getId());
    }

    public int delete(Integer id) {
        String sql = "DELETE FROM echeances WHERE id_echeance = ?";
        return jdbcTemplate.update(sql, id);
    }

    public List<Echeance> findByFacture(Integer factureId) {
        String sql = "SELECT id_echeance, date_echeance, montant, payer, id_facture FROM echeances WHERE id_facture = ?";
        return jdbcTemplate.query(sql, new Object[]{factureId}, (rs, rowNum) -> {
            Echeance echeance = new Echeance();
            echeance.setId(rs.getInt("id_echeance"));
            echeance.setDateEcheance(rs.getObject("date_echeance", LocalDate.class));
            echeance.setMontant(rs.getBigDecimal("montant"));
            echeance.setPayer(rs.getBoolean("payer"));
            echeance.setIdFacture(rs.getInt("id_facture"));
            return echeance;
        });
    }
}
