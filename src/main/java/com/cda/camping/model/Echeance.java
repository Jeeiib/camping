package com.cda.camping.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "echeances")
public class Echeance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_echeance")
    private Integer id;

    @Column(name = "date_echeance")
    private LocalDate dateEcheance;

    @Column(name = "montant")
    private BigDecimal montant;

    @Column(name = "payer")
    private Boolean payer;

    @Column(name = "id_facture", nullable = false)
    private Integer idFacture;

    public Echeance() {
    }

    public Echeance(LocalDate dateEcheance, BigDecimal montant, Boolean payer, Integer idFacture) {
        this.dateEcheance = dateEcheance;
        this.montant = montant;
        this.payer = payer;
        this.idFacture = idFacture;
    }

    @Override
    public String toString() { return "Echeance{" + "id=" + id + ", dateEcheance=" + dateEcheance + ", montant=" + montant + ", payer=" + payer + ", idFacture=" + idFacture + '}'; }
}
