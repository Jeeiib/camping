package com.cda.camping.repository;

import com.cda.camping.model.FactureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public class FactureServiceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public FactureService findById(Integer id) {
        String sql = "SELECT id_facture, date_facture, prix_ttc, prix_ht, facture, id_services FROM factures_services WHERE id_facture = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new FactureService(rs.getObject("date_facture", LocalDate.class), rs.getBigDecimal("prix_ttc"), rs.getBigDecimal("prix_ht"), rs.getBoolean("facture"), rs.getInt("id_services")));
    }

    public List<FactureService> findAll() {
        String sql = "SELECT id_facture, date_facture, prix_ttc, prix_ht, facture, id_services FROM factures_services";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            FactureService factureService = new FactureService();
            factureService.setId(rs.getInt("id_facture"));
            factureService.setDateFacture(rs.getObject("date_facture", LocalDate.class));
            factureService.setPrixTtc(rs.getBigDecimal("prix_ttc"));
            factureService.setPrixHt(rs.getBigDecimal("prix_ht"));
            factureService.setFacture(rs.getBoolean("facture"));
            factureService.setIdServices(rs.getInt("id_services"));
            return factureService;
        });
    }

    public int save(FactureService factureService) {
        String sql = "INSERT INTO factures_services (date_facture, prix_ttc, prix_ht, facture, id_services) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, factureService.getDateFacture(), factureService.getPrixTtc(), factureService.getPrixHt(), factureService.getFacture(), factureService.getIdServices());
    }

    public int update(FactureService factureService) {
        String sql = "UPDATE factures_services SET date_facture = ?, prix_ttc = ?, prix_ht = ?, facture = ?, id_services = ? WHERE id_facture = ?";
        return jdbcTemplate.update(sql, factureService.getDateFacture(), factureService.getPrixTtc(), factureService.getPrixHt(), factureService.getFacture(), factureService.getIdServices(), factureService.getId());
    }

    public int delete(Integer id) {
        String sql = "DELETE FROM factures_services WHERE id_facture = ?";
        return jdbcTemplate.update(sql, id);
    }

    public FactureService findByServiceId(Integer serviceId) {
        String sql = "SELECT id_facture, date_facture, prix_ttc, prix_ht, facture, id_services FROM factures_services WHERE id_services = ?";
        List<FactureService> factureServices = jdbcTemplate.query(sql, new Object[]{serviceId}, (rs, rowNum) -> new FactureService(rs.getObject("date_facture", LocalDate.class), rs.getBigDecimal("prix_ttc"), rs.getBigDecimal("prix_ht"), rs.getBoolean("facture"), rs.getInt("id_services")));
        return factureServices.isEmpty() ? null : factureServices.get(0);
    }
}





