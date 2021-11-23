/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.veterinaria.modelo.repository;

import com.example.veterinaria.modelo.Turno;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Skynet
 */
@Repository
public interface RepoTurnos extends JpaRepository<Turno, String>{
    
    public List<Turno> findByIddoctor(String iddoctor);
    public List<Turno> findByDiaAndHorario(String Dia, String Horario);
    public List<Turno> findByDiaAndHorarioAndIddoctor(String Dia, String Horario, String iddoctor);
    public List<Turno> findByDia(String dia);
    public List<Turno> findByHorario(String Horario);
}
