package com.cda.camping.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resa")
    private Integer id;

    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "id_hebergement", nullable = false)
    private Integer idHebergement;

    @Column(name = "id_client", nullable = false)
    private Integer idClient;

    @ManyToOne
    @JoinColumn(name = "id_hebergement", referencedColumnName = "id_hebergement", insertable = false, updatable = false)
    private Hebergement hebergement;

    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id_client", insertable = false, updatable = false)
    private Client client;

    public Reservation() {
    }

    public Reservation(LocalDate dateDebut, LocalDate dateFin, Integer idHebergement, Integer idClient) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idHebergement = idHebergement;
        this.idClient = idClient;
    }

}
