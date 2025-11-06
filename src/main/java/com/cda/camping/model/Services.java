package com.cda.camping.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "services")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_services")
    private Integer id;

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "prix")
    private BigDecimal prix;

    public Services() {
    }

    public Services(String label, BigDecimal prix) {
        this.label = label;
        this.prix = prix;
    }

    @Override
    public String toString() { return "Services{" + "id=" + id + ", label='" + label + '\'' + ", prix=" + prix + '}'; }
}
