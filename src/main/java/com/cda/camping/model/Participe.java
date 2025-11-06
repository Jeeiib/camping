package com.cda.camping.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "participe")
@IdClass(ParticipeId.class)
public class Participe {

    @Id
    @Column(name = "id_client")
    private Integer idClient;

    @Id
    @Column(name = "id_services")
    private Integer idServices;

    @Column(name = "date_debut")
    private LocalDateTime dateDebut;

    @Column(name = "date_fin")
    private LocalDateTime dateFin;

    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id_client", insertable = false, updatable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_services", referencedColumnName = "id_services", insertable = false, updatable = false)
    private Services service;

    public Participe() {
    }

    public Participe(Integer idClient, Integer idServices, LocalDateTime dateDebut, LocalDateTime dateFin) {
        this.idClient = idClient;
        this.idServices = idServices;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
}
