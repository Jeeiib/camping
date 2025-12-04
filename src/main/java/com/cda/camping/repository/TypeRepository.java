package com.cda.camping.repository;

import com.cda.camping.model.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TypeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Type findById(Integer id) {
        String sql = "SELECT id_type, label FROM types WHERE id_type = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new Type(rs.getString("label")));
    }

    public List<Type> findAll() {
        String sql = "SELECT id_type, label FROM types";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Type type = new Type();
            type.setId(rs.getInt("id_type"));
            type.setLabel(rs.getString("label"));
            return type;
        });
    }

    public int save(Type type) {
        String sql = "INSERT INTO types (label) VALUES (?)";
        return jdbcTemplate.update(sql, type.getLabel());
    }

    public int update(Type type) {
        String sql = "UPDATE types SET label = ? WHERE id_type = ?";
        return jdbcTemplate.update(sql, type.getLabel(), type.getId());
    }

    public int delete(Integer id) {
        String sql = "DELETE FROM types WHERE id_type = ?";
        return jdbcTemplate.update(sql, id);
    }

    public Type findByLabel(String label) {
        String sql = "SELECT id_type, label FROM types WHERE label = ?";
        List<Type> types = jdbcTemplate.query(sql, new Object[]{label}, (rs, rowNum) -> new Type(rs.getString("label")));
        return types.isEmpty() ? null : (Type) types;
    }
}


