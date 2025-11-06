package com.cda.camping.repository;

import com.cda.camping.model.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServiceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Services findById(Integer id) {
        String sql = "SELECT id_services, label, prix FROM services WHERE id_services = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new Services(rs.getString("label"), rs.getBigDecimal("prix")));
    }

    public List<Services> findAll() {
        String sql = "SELECT id_services, label, prix FROM services";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Services Services = new Services();
            Services.setId(rs.getInt("id_services"));
            Services.setLabel(rs.getString("label"));
            Services.setPrix(rs.getBigDecimal("prix"));
            return Services;
        });
    }

    public int save(Services Services) {
        String sql = "INSERT INTO services (label, prix) VALUES (?, ?)";
        return jdbcTemplate.update(sql, Services.getLabel(), Services.getPrix());
    }

    public int update(Services Services) {
        String sql = "UPDATE services SET label = ?, prix = ? WHERE id_services = ?";
        return jdbcTemplate.update(sql, Services.getLabel(), Services.getPrix(), Services.getId());
    }

    public int delete(Integer id) {
        String sql = "DELETE FROM services WHERE id_services = ?";
        return jdbcTemplate.update(sql, id);
    }

    public Services findByLabel(String label) {
        String sql = "SELECT id_services, label, prix FROM services WHERE label = ?";
        List<Services> services = jdbcTemplate.query(sql, new Object[]{label}, (rs, rowNum) -> {
            Services Services = new Services();
            Services.setId(rs.getInt("id_services"));
            Services.setLabel(rs.getString("label"));
            Services.setPrix(rs.getBigDecimal("prix"));
            return Services;
        });
        return services.isEmpty() ? null : services.get(0);
    }
}




