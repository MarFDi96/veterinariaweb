/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.veterinaria.controlador;

import com.example.veterinaria.modelo.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Skynet
 */
@Controller
public class Index {
    
    @GetMapping("/")
    public String loginForm() {
        return "index";
    }
    /*@GetMapping("/")
    public String index(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "index";
    }*/
}
