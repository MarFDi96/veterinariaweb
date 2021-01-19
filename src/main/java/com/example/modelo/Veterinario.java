package com.example.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Veterinario")
@DiscriminatorValue("Veterinario")
public class Veterinario extends Usuario {

    public Veterinario(String id, String nombre, String password) {
        super(id, nombre, password);
    }

}