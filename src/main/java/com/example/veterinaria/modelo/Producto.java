/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.veterinaria.modelo;

import com.sun.istack.NotNull;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author Skynet
 */
@Entity
@Table(name = "productos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "categoria")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true, insertable = false)
    String id;

    @NotNull
    @Column(nullable = false)
    String descripcion;

    @NotNull
    @Column(nullable = false)
    Integer cantidad;

    @NotNull
    @Column(nullable = false)
    String categoria;
    
    @NotNull
    @Column(nullable = false)
    Double precio;

    public Producto(String id, String descripcion, Integer cantidad, String categoria, Double precio) {
        this.id = id;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.precio = precio;
    }

    public Producto() {
    }

    public Producto(String descripcion, Integer cantidad, String categoria, Double precio) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.precio = precio;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    
}
