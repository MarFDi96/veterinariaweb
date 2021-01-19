package com.example.modelo;


import com.sun.istack.NotNull;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "usuarios")
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

    public Usuario(String id, String nombre, String password) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
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
