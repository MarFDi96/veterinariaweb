package com.example.veterinaria.modelo;

import com.sun.istack.NotNull;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "rol")
public class Usuario implements Serializable {

    @Id
    @Column(updatable = false, nullable = false, unique = true, insertable = false)
    String id;

    @NotNull
    @Column(nullable = false)
    String nombre;

    @NotNull
    @Column(nullable = false)
    String password;

    @Column(updatable = false, insertable = false)
    private String rol;

    @NotNull
    @Column(nullable = false)
    String diaslaborales;
    
    @Column
    String manejoanimal;

    public Usuario(String id, String nombre, String password, String diaslaborales) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.diaslaborales = diaslaborales;
    }

    public Usuario() {

    }

    public String getManejoanimal() {
        return manejoanimal;
    }

    public void setManejoanimal(String manejoanimal) {
        this.manejoanimal = manejoanimal;
    }
    

    public String getDiaslaborales() {
        return diaslaborales;
    }

    public void setDiaslaborales(String diaslaborales) {
        this.diaslaborales = diaslaborales;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
