package com.cda.camping.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "factures")
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_facture")
    private Integer id;

    @Column(name = "date_facture")
    private LocalDate dateFacture;

    @Column(name = "prix_ttc")
    private BigDecimal prixTtc;

    @Column(name = "prix_ht")
    private BigDecimal prixHt;

    @Column(name = "facture")
    private Boolean facture;

    @Column(name = "id_resa", unique = true, nullable = false)
    private Integer idResa;

    @OneToOne
    @JoinColumn(name = "id_resa", referencedColumnName = "id_resa", insertable = false, updatable = false)
    private Reservation reservation;

    public Facture() {
    }

    public Facture(LocalDate dateFacture, BigDecimal prixTtc, BigDecimal prixHt, Boolean facture, Integer idResa) {
        this.dateFacture = dateFacture;
        this.prixTtc = prixTtc;
        this.prixHt = prixHt;
        this.facture = facture;
        this.idResa = idResa;
    }
}
