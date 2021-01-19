package com.example.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Recepcionista")
@DiscriminatorValue("Recepcionista")
public class Recepcionista extends Usuario {

    public Recepcionista(String id, String nombre, String password) {
        super(id, nombre, password);
    }

}
