package com.example.veterinaria.controlador;

import com.example.veterinaria.modelo.repository.RepoUsuarios;
import com.example.veterinaria.modelo.Usuario;
import com.example.veterinaria.modelo.Turno;
import com.example.veterinaria.modelo.Recepcionista;
import com.example.veterinaria.modelo.Veterinario;
import com.example.veterinaria.modelo.repository.RepoTurnos;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ABMUsuarios {

    @Autowired
    RepoUsuarios ru;

    @Autowired
    RepoTurnos rt;

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
            redirectAttributes.addFlashAttribute("errormsg", "Los días no pueden estar repetidos");
            return "redirect:/error";
        } else if (!rt.findByIddoctor(admin).isEmpty()) {
            redirectAttributes.addFlashAttribute("errormsg", "No se pudo realizar el cambio, quedan turnos pendientes");
            return "redirect:/error";
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
            @RequestParam(name = "animal1") Optional<String> animalUno,
            @RequestParam(name = "animal2") Optional<String> animalDos,
            @RequestParam(name = "animal3") Optional<String> animalTres,
            @RequestParam(name = "animal4") Optional<String> animalCuatro,
            @RequestParam(name = "admin") String doctor) {

        Usuario usuario;
        usuario = ru.findById(doctor).get();
        boolean turnosFlag;
        String manejoAnimal = "";

        if (animalUno.isPresent()) {
            manejoAnimal += animalUno.get();
        }
        if (animalDos.isPresent() && !animalDos.equals(animalUno)) {

            manejoAnimal += ", " + animalDos.get();
        }

        //animalDos.ifPresent ( manejoAnimal += ", " + animalDos.get());
        if (animalTres.isPresent() && !animalTres.equals(animalDos)
                && !animalTres.equals(animalUno)) {

            manejoAnimal += ", " + animalTres.get();
        }
        if (animalCuatro.isPresent() && !animalCuatro.equals(animalTres)
                && !animalCuatro.equals(animalDos) && !animalCuatro.equals(animalUno)) {

            manejoAnimal += ", " + animalCuatro.get();
        }
        for(Turno t: rt.findByIddoctor(doctor)){

            if (!manejoAnimal.contains(t.getTipoanimal())){
                redirectAttributes.addFlashAttribute("errormsg", "No se pudo realizar el cambio, quedan turnos con animales de la anterior configuración");
            return "redirect:/error";
            }
        }

        usuario.setManejoanimal(manejoAnimal);
        ru.saveAndFlush(usuario);
        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#users";

    }

}
