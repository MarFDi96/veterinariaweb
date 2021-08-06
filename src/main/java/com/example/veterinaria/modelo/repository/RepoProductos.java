/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.veterinaria.modelo.repository;

import com.example.veterinaria.modelo.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Skynet
 */
@Repository
public interface RepoProductos extends JpaRepository<Producto, String>{
    
    public List<Producto> findByDescripcion(String descripcion);
    
    public List<Producto> findByCategoria(String categoria);
}
