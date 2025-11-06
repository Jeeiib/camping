package com.cda.camping.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ParticipeId implements Serializable {

    private Integer idClient;
    private Integer idServices;

    public ParticipeId() {
    }

    public ParticipeId(Integer idClient, Integer idServices) {
        this.idClient = idClient;
        this.idServices = idServices;
    }

    // Equals and HashCode methods must be implemented

}
