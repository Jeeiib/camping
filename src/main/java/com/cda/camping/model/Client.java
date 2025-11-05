package com.cda.camping.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Entité JPA représentant un client du camping
 *
 * Cette classe est un modèle de données (POJO - Plain Old Java Object) qui représente
 * la table "clients" de la base de données.
 *
 * Annotations Lombok :
 * @Getter : Génère automatiquement les getters pour tous les attributs
 * @Setter : Génère automatiquement les setters pour tous les attributs
 *
 * Annotations JPA :
 * @Entity : Indique que cette classe est une entité JPA (correspond à une table)
 * @Table : Spécifie le nom de la table dans la base de données
 */
@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client {

    /**
     * Identifiant unique du client (clé primaire)
     *
     * @Id : Indique que c'est la clé primaire
     * @GeneratedValue : La valeur est générée automatiquement par la base de données
     * @Column : Spécifie le nom de la colonne dans la table
     */
    @Id
    @Column(name = "id_client")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nom de famille du client
     */
    @Column(name = "nom")
    private String nom;

    /**
     * Prénom du client
     */
    @Column(name = "prenom")
    private String prenom;

    /**
     * Adresse postale du client
     */
    @Column(name = "adresse")
    private String adresse;

    /**
     * Numéro de téléphone du client
     * Longueur maximale : 15 caractères
     */
    @Column(name = "telephone", length=15)
    private String telephone;

    /**
     * Identifiant de l'utilisateur associé au client
     * (Clé étrangère vers une table users qui n'est pas encore implémentée)
     */
    @Column(name = "id_user")
    private Integer idUser;
}
