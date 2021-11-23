package com.example.veterinaria.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Veterinario")
@DiscriminatorValue("Veterinario")
public class Veterinario extends Usuario {
    
    public Veterinario(){
        
    }

    public Veterinario(String id, String nombre, String password, String diaslaborales) {
        super(id, nombre, password, diaslaborales);
    }

}