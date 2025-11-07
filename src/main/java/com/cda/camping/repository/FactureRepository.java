package com.cda.camping.repository;

import com.cda.camping.model.Facture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class FactureRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Facture findById(Integer id) {
        String sql = "SELECT id_facture, date_facture, prix_ttc, prix_ht, facture, id_resa FROM factures WHERE id_facture = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Facture facture = new Facture();
            facture.setId(rs.getInt("id_facture"));
            facture.setDateFacture(rs.getObject("date_facture", LocalDate.class));
            facture.setPrixTtc(rs.getBigDecimal("prix_ttc"));
            facture.setPrixHt(rs.getBigDecimal("prix_ht"));
            facture.setFacture(rs.getBoolean("facture"));
            facture.setIdResa(rs.getInt("id_resa"));
            return facture;
        });
    }

    public List<Facture> findAll() {
        String sql = "SELECT id_facture, date_facture, prix_ttc, prix_ht, facture, id_resa FROM factures";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Facture facture = new Facture();
            facture.setId(rs.getInt("id_facture"));
            facture.setDateFacture(rs.getObject("date_facture", LocalDate.class));
            facture.setPrixTtc(rs.getBigDecimal("prix_ttc"));
            facture.setPrixHt(rs.getBigDecimal("prix_ht"));
            facture.setFacture(rs.getBoolean("facture"));
            facture.setIdResa(rs.getInt("id_resa"));
            return facture;
        });
    }

    public int save(Facture facture) {
        String sql = "INSERT INTO factures (date_facture, prix_ttc, prix_ht, facture, id_resa) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, facture.getDateFacture(), facture.getPrixTtc(), facture.getPrixHt(), facture.getFacture(), facture.getIdResa());
    }

    public int update(Facture facture) {
        String sql = "UPDATE factures SET date_facture = ?, prix_ttc = ?, prix_ht = ?, facture = ?, id_resa = ? WHERE id_facture = ?";
        return jdbcTemplate.update(sql, facture.getDateFacture(), facture.getPrixTtc(), facture.getPrixHt(), facture.getFacture(), facture.getIdResa(), facture.getId());
    }

    public int delete(Integer id) {
        String sql = "DELETE FROM factures WHERE id_facture = ?";
        return jdbcTemplate.update(sql, id);
    }

    public Facture findByReservationId(Integer resaId) {
        String sql = "SELECT id_facture, date_facture, prix_ttc, prix_ht, facture, id_resa FROM factures WHERE id_resa = ?";
        List<Facture> factures = jdbcTemplate.query(sql, new Object[]{resaId}, (rs, rowNum) -> new Facture(rs.getObject("date_facture", LocalDate.class), rs.getBigDecimal("prix_ttc"), rs.getBigDecimal("prix_ht"), rs.getBoolean("facture"), rs.getInt("id_resa")));
        return factures.isEmpty() ? null : factures.get(0);
    }
}
