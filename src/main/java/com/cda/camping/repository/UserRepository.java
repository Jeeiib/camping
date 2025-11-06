package com.cda.camping.repository;

import com.cda.camping.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findById(Integer id) {
        String sql = "SELECT id_user, login, password, roles FROM users WHERE id_user = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new User(rs.getString("login"), rs.getString("password"), rs.getString("roles")));
    }

    public List<User> findAll() {
        String sql = "SELECT id_user, login, password, roles FROM users";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id_user"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setRoles(rs.getString("roles"));
            return user;
        });
    }

    public int save(User user) {
        String sql = "INSERT INTO users (login, password, roles) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, user.getLogin(), user.getPassword(), user.getRoles());
    }

    public int update(User user) {
        String sql = "UPDATE users SET login = ?, password = ?, roles = ? WHERE id_user = ?";
        return jdbcTemplate.update(sql, user.getLogin(), user.getPassword(), user.getRoles(), user.getId());
    }

    public int delete(Integer id) {
        String sql = "DELETE FROM users WHERE id_user = ?";
        return jdbcTemplate.update(sql, id);
    }

    public User findByLogin(String login) {
        String sql = "SELECT id_user, login, password, roles FROM users WHERE login = ?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{login}, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id_user"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setRoles(rs.getString("roles"));
            return user;
        });
        return users.isEmpty() ? null : users.get(0);
    }
}

