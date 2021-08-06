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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Skynet
 */
@Entity
@Table(name = "turnos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "numerocontacto")
public class Turno implements Serializable{   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, insertable = false)
    String id;
    
    @NotNull
    @Column(nullable = false)
    String iddoctor;
    
    @NotNull
    @Column(nullable = false)
   private String horario;
    
    @NotNull
    @Column(nullable = false)
    public String nombrepropietario;
    
    @NotNull
    @Column(nullable = false)
    String nombremascota;
    
    @NotNull
    @Column(nullable = false)
    String tipoanimal;
    
    @Column(nullable = false)
    String numerocontacto;
    
    @NotNull
    @Column(nullable = false)
    String dia;

    public Turno(String id, String iddoctor, String horario, String nombrepropietario,
            String nombremascota, String tipoanimal, String numerocontacto, String dia) {
        this.id = id;
        this.iddoctor = iddoctor;
        this.horario = horario;
        this.nombrepropietario = nombrepropietario;
        this.nombremascota = nombremascota;
        this.tipoanimal = tipoanimal;
        this.numerocontacto = numerocontacto;
        this.dia = dia;
    }
    
    public Turno() {
    }

    public Turno(String iddoctor, String horario, String nombrepropietario, String nombremascota,
            String tipoanimal, String numerocontacto, String dia) {
        this.iddoctor = iddoctor;
        this.horario = horario;
        this.nombrepropietario = nombrepropietario;
        this.nombremascota = nombremascota;
        this.tipoanimal = tipoanimal;
        this.numerocontacto = numerocontacto;
        this.dia = dia;
    }
    
    


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIddoctor() {
        return iddoctor;
    }

    public void setIddoctor(String iddoctor) {
        this.iddoctor = iddoctor;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getnombrepropietario() {
        return nombrepropietario;
    }

    public void setnombrepropietario(String nombrepropietario) {
        this.nombrepropietario = nombrepropietario;
    }

    public String getNombremascota() {
        return nombremascota;
    }

    public void setNombremascota(String nombremascota) {
        this.nombremascota = nombremascota;
    }

    public String getTipoanimal() {
        return tipoanimal;
    }

    public void setTipoanimal(String tipoanimal) {
        this.tipoanimal = tipoanimal;
    }

    public String getNumerocontacto() {
        return numerocontacto;
    }

    public void setNumerocontacto(String numerocontacto) {
        this.numerocontacto = numerocontacto;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
    
    
    
    
}
