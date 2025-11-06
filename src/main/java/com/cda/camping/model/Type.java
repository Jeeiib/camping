package com.cda.camping.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "types")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type")
    private Integer id;

    @Column(name = "label")
    private String label;

    public Type() {
    }

    public Type(String label) {
        this.label = label;
    }

    @Override
    public String toString() { return "Type{" + "id=" + id + ", label='" + label + '\'' + '}'; }
}
