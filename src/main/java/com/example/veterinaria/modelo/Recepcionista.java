package com.example.veterinaria.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Recepcionista")
@DiscriminatorValue("Recepcionista")
public class Recepcionista extends Usuario {

    public Recepcionista(){
        
    }
    
    public Recepcionista(String id, String nombre, String password, String diaslaborales) {
        super(id, nombre, password, diaslaborales);
    }

}
