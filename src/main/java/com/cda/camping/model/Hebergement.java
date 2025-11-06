package com.cda.camping.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hebergements")
public class Hebergement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hebergement")
    private Integer id;

    @Column(name = "emplacement")
    private String emplacement;

    @Column(name = "etat")
    private String etat;

    @Column(name = "id_type")
    private Integer idType;

    @ManyToOne
    @JoinColumn(name = "id_type", referencedColumnName = "id_type", insertable = false, updatable = false)
    private Type type;

    public Hebergement() {
    }

    public Hebergement(String emplacement, String etat, Integer idType) {
        this.emplacement = emplacement;
        this.etat = etat;
        this.idType = idType;
    }

    @Override
    public String toString() {
        return "Hebergement{" + "id=" + id + ", emplacement='" + emplacement + '\'' + ", etat='" + etat + '\'' + ", idType=" + idType + '}';
    }
}
