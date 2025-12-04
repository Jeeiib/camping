package com.cda.camping.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.fasterxml.jackson.databind.ObjectMapper; 



/**
 * Classe de configuration Spring
 *
 * Cette classe remplace les fichiers de configuration XML traditionnels.
 * Elle définit tous les beans nécessaires au fonctionnement de l'application.
 *
 * @Configuration : Indique que cette classe contient des définitions de beans Spring
 * @EnableWebMvc : Active la configuration Spring MVC (gestion des controllers, JSON, etc.)
 * @ComponentScan : Scanne automatiquement les composants Spring (@Controller, @Service, @Repository)
 *                  dans le package "com.cda.camping" et ses sous-packages
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.cda.camping", "org.springdoc"})
public class AppConfig {

    /**
     * Configure la source de données (connexion à la base de données MySQL)
     *
     * @Bean : Indique que cette méthode produit un bean géré par Spring
     * Le bean DataSource sera automatiquement injecté partout où il est nécessaire
     *
     * @return DataSource configuré pour MySQL
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // Driver JDBC pour MySQL
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/camping");
        dataSource.setUsername("jayb");
        dataSource.setPassword("");
        return dataSource;
    }

    /**
     * Configure JdbcTemplate, l'outil Spring pour exécuter des requêtes SQL
     *
     * JdbcTemplate simplifie l'utilisation de JDBC en gérant automatiquement
     * l'ouverture/fermeture des connexions et la gestion des exceptions
     *
     * @param dataSource la source de données injectée automatiquement par Spring
     * @return JdbcTemplate configuré et prêt à l'emploi
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * Configure ObjectMapper pour la sérialisation/désérialisation JSON
     *
     * ObjectMapper (de Jackson) convertit automatiquement les objets Java en JSON
     * et vice-versa pour les API REST
     *
     * @return ObjectMapper configuré
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
        /**
     * Configure l'encodeur de mots de passe
     *
     * BCrypt est l'implémentation recommandée pour le hachage de mots de passe.
     * Il est sécurisé car il inclut un "sel" aléatoire pour chaque mot de passe.
     * @return un bean PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
