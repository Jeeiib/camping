package com.cda.camping.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    private String roles;

    public User() {
    }

    public User(String login, String password, String roles) {
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", login='" + login + '\'' + ", password='" + password + '\'' + ", roles='" + roles + '\'' + '}';
    }
}

