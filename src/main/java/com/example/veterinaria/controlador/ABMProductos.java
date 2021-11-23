/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.veterinaria.controlador;

import com.example.veterinaria.modelo.repository.RepoUsuarios;
import com.example.veterinaria.modelo.repository.RepoProductos;
import com.example.veterinaria.modelo.Usuario;
import com.example.veterinaria.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ABMProductos {

    @Autowired            
    RepoProductos rp;
    @Autowired
    RepoUsuarios ru;
    
    @PostMapping("/nuevo-producto")
    public String nuevoProducto(final RedirectAttributes redirectAttributes,
            @RequestParam(name = "id") String id,
            @RequestParam(name = "descripcion") String descripcion,
            @RequestParam(name = "cantidad") Integer cantidad,
            @RequestParam(name = "categoria") String categoria,
            @RequestParam(name = "precio") Double precio,
            @RequestParam(name = "admin") String admin){
        
        if(cantidad >= 0 && precio >= 0){
            rp.saveAndFlush(new Producto(descripcion, cantidad, categoria, precio));
        }
        Usuario usuario;
        usuario = ru.findById(admin).get();
        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#productos";
    }

    @PostMapping("/editar-borrar-producto")
    public String editarBorrarProducto(final RedirectAttributes redirectAttributes,
            @RequestParam(name = "id") String id,
            @RequestParam(name = "descripcion") String descripcion,
            @RequestParam(name = "cantidad") Integer cantidad,
            @RequestParam(name = "categoria") String categoria,
            @RequestParam(name = "precio") Double precio,
            @RequestParam(name = "admin") String admin,
            @RequestParam(name = "accion") String accion) {
        if(accion.equals("Borrar")) {
            rp.deleteById(id);
        } else if(accion.equals("Editar") && cantidad >= 0 && precio >= 0){
            Producto editado = rp.findById(id).get();
            editado.setId(id);
            editado.setDescripcion(descripcion);
            editado.setCantidad(cantidad);
            editado.setCategoria(categoria);
            editado.setPrecio(precio);
            rp.saveAndFlush(editado);
        }
        Usuario usuario;
        usuario = ru.findById(admin).get();
        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#productos";
    }
    
        @PostMapping("/vender-producto")
    public String venderProducto(final RedirectAttributes redirectAttributes,
            @RequestParam(name = "id") String id,
            @RequestParam(name = "descripcion") String descripcion,
            @RequestParam(name = "cantidad") Integer cantidad,
            @RequestParam(name = "cantidadventa") Integer cantidadventa,
            @RequestParam(name = "categoria") String categoria,
            @RequestParam(name = "precio") Double precio,
            @RequestParam(name = "admin") String admin,
            @RequestParam(name = "accion") String accion) {
        
        if(cantidad >= cantidadventa){
            Producto editado = rp.findById(id).get();
            editado.setId(id);
            editado.setDescripcion(descripcion);
            editado.setCantidad(cantidad-cantidadventa);
            editado.setCategoria(categoria);
            editado.setPrecio(precio);
            rp.saveAndFlush(editado);
        }
        Usuario usuario;
        usuario = ru.findById(admin).get();
        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#productos";
    }
}
