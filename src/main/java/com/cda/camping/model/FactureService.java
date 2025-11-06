package com.cda.camping.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "factures_services")
public class FactureService {

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

    @Column(name = "id_services", unique = true, nullable = false)
    private Integer idServices;

    public FactureService() {
    }

    public FactureService(LocalDate dateFacture, BigDecimal prixTtc, BigDecimal prixHt, Boolean facture, Integer idServices) {
        this.dateFacture = dateFacture;
        this.prixTtc = prixTtc;
        this.prixHt = prixHt;
        this.facture = facture;
        this.idServices = idServices;
    }
}

