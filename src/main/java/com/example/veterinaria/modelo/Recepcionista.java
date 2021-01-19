package com.example.veterinaria.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Recepcionista")
@DiscriminatorValue("Recepcionista")
public class Recepcionista extends Usuario {

    public Recepcionista(){
        
    }
    
    public Recepcionista(String id, String nombre, String password) {
        super(id, nombre, password);
    }

}
