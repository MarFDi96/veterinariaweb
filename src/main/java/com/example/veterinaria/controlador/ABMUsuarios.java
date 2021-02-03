package com.example.veterinaria.controlador;

import com.example.veterinaria.modelo.RepoUsuarios;
import com.example.veterinaria.modelo.Usuario;
import com.example.veterinaria.modelo.Recepcionista;
import com.example.veterinaria.modelo.Veterinario;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ABMUsuarios {
    
    @Autowired
    RepoUsuarios ru;

    //TODO
    //AGREGAR FUNCION PARA QUE EL DOCTOR CAMBIE SUS DIAS LABORALES
    @PostMapping("/nuevo-usuario")
    public String nuevoUsuario(final RedirectAttributes redirectAttributes,
            @RequestParam(name = "id") String id,
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "rol") String rol,
            @RequestParam(name = "diaslaborales") String diaslaborales,
            @RequestParam(name = "admin") String admin) {
        
        if (rol.equals("Veterinario")) {
            ru.saveAndFlush(new Veterinario(id, nombre, password, diaslaborales));
        } else {
            ru.saveAndFlush(new Recepcionista(id, nombre, password, diaslaborales));
        }
        Usuario usuario;
        usuario = ru.findById(admin).get();
        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#users";
    }
    
    @PostMapping("/editar-borrar-usuario")
    public String editarBorrarUsuario(final RedirectAttributes redirectAttributes,
            @RequestParam(name = "id") String id,
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "rol") String rol,
            @RequestParam(name = "admin") String admin,
            @RequestParam(name = "accion") String accion) {
        if (accion.equals("Borrar")) {
            ru.deleteById(id);
        } else if (accion.equals("Editar")) {
            Usuario editado = ru.findById(id).get();
            editado.setNombre(nombre);
            editado.setPassword(password);
            ru.saveAndFlush(editado);
        }
        Usuario usuario;
        usuario = ru.findById(admin).get();
        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#users";
    }
    
    @PostMapping("/editar-veterinario-dias")
    public String editarDiasVeterinario(final RedirectAttributes redirectAttributes,
            @RequestParam(name = "dia1") String diauno,
            @RequestParam(name = "dia2") String diados,
            @RequestParam(name = "dia3") String diatres,
            @RequestParam(name = "admin") String admin) {
        
        if (diauno.equals(diados) || diauno.equals(diatres) || diados.equals(diatres)) {
            return "redirect:/login#config";
        } else {
            Usuario usuario;
            usuario = ru.findById(admin).get();
            usuario.setDiaslaborales(diauno + "," + diados + "," + diatres);
            ru.saveAndFlush(usuario);
            redirectAttributes.addFlashAttribute("usuario", usuario);
            return "redirect:/login#users";
        }
        
    }
    
        @PostMapping("/editar-veterinario-animales")
    public String editarAnimalesVeterinario(final RedirectAttributes redirectAttributes,
            @RequestParam(name = "animal1") String animalUno,
            @RequestParam(name = "animal2") Optional<String> animalDos,
            @RequestParam(name = "animal3") Optional <String> animalTres,
            @RequestParam(name = "animal4") Optional <String> animalCuatro,
            @RequestParam(name = "admin") String admin) {
        
        if (animalUno.equals(animalDos) || animalUno.equals(animalTres) ||animalUno.equals(animalCuatro)||
                                           animalDos.equals(animalTres)|| animalDos.equals(animalCuatro)) {
            return "redirect:/login#config";
        } else {
            String animalHandling = "";
            
            animalHandling += animalUno;
            
            if(animalDos.isPresent()){
                animalHandling += ", " + animalDos.get();
            }          
            //animalDos.ifPresent ( animalHandling += ", " + animalDos.get());
            if (animalTres.isPresent()){
                animalHandling += ", " + animalTres.get();
            }
            if (animalCuatro.isPresent()){
                animalHandling += ", " + animalCuatro.get();
            }
            
            Usuario usuario;
            usuario = ru.findById(admin).get();
            usuario.setManejoanimal(animalHandling);
            ru.saveAndFlush(usuario);
            redirectAttributes.addFlashAttribute("usuario", usuario);
            return "redirect:/login#users";
        }
        
    }
    

}
