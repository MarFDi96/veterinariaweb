package com.example.veterinaria.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Admin")
@DiscriminatorValue("Admin")
public class Admin extends Usuario {

        public Admin() {
    }
        
    public Admin(String id, String nombre, String password, String diaslaborales) {
        super(id, nombre, password, diaslaborales);
    }

}
