package com.example.veterinaria.controlador;


import com.example.veterinaria.modelo.RepoUsuarios;
import com.example.veterinaria.modelo.Usuario;
import com.example.veterinaria.modelo.Recepcionista;
import com.example.veterinaria.modelo.Veterinario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ABMUsuarios {

    @Autowired
    RepoUsuarios ru;
    
    @PostMapping("/nuevo-usuario")
    public String nuevoUsuario(final RedirectAttributes redirectAttributes,
            @RequestParam(name = "id") String id,
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "rol") String rol,
            @RequestParam(name = "admin") String admin) {

        if (rol.equals("Veterinario")) {
            ru.saveAndFlush(new Veterinario(id, nombre, password));
        } else {
            ru.saveAndFlush(new Recepcionista(id, nombre, password));
        }
        Usuario usuario;
        usuario = ru.findById(admin).get();
        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#users";
    }

    @PostMapping("/editar-borrar-usuario")
    public String editarBorrarUsuario(final RedirectAttributes redirectAttributes,
            @RequestParam(name = "codigo") String codigo,
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "rol") String rol,
            @RequestParam(name = "admin") String admin,
            @RequestParam(name = "accion") String accion) {
        if(accion.equals("Borrar")) {
            ru.deleteById(codigo);
        } else if(accion.equals("Editar")){
            Usuario editado = ru.findById(codigo).get();
            editado.setNombre(nombre);
            editado.setPassword(password);
            ru.saveAndFlush(editado);
        }
        Usuario usuario;
        usuario = ru.findById(admin).get();
        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#users";
    }
    
}
