package com.example.veterinaria.controlador;

import com.example.veterinaria.modelo.RepoTurnos;
import com.example.veterinaria.modelo.RepoUsuarios;
import com.example.veterinaria.modelo.Usuario;
import com.example.veterinaria.modelo.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ABMTurnos {

    @Autowired
    RepoTurnos rt;
    @Autowired
    RepoUsuarios ru;

    @PostMapping("/nuevo-turno")
    public String nuevoTurno(final RedirectAttributes redirectAttributes,
            @RequestParam(name = "iddoctor") String iddoctor,
            @RequestParam(name = "horario") String horario,
            @RequestParam(name = "nombrepropietario") String nombrepropietario,
            @RequestParam(name = "nombremascota") String nombremascota,
            @RequestParam(name = "tipoanimal") String tipoanimal,
            @RequestParam(name = "numerocontacto") String numerocontacto,
            @RequestParam(name = "admin") String admin,
            @RequestParam(name = "dia") String dia) {

        Usuario doctor;
        doctor = ru.findById(iddoctor).get();
        Turno turno = new Turno(iddoctor, horario, nombrepropietario,
                                nombremascota, tipoanimal, numerocontacto, dia);

        if (doctor.getDiaslaborales().contains(dia)
                && doctor.getManejoanimal().contains(tipoanimal)) {
            rt.saveAndFlush(turno);
        }
        Usuario usuario;
        usuario = ru.findById(admin).get();
        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#turnos";
    }

    @PostMapping("/editar-borrar-turno")
    public String editarBorrarUsuario(final RedirectAttributes redirectAttributes,
            @RequestParam(name = "iddoctor") String iddoctor,
            @RequestParam(name = "horario") String horario,
            @RequestParam(name = "nombrepropietario") String nombrepropietario,
            @RequestParam(name = "nombremascota") String nombremascota,
            @RequestParam(name = "tipoanimal") String tipoanimal,
            @RequestParam(name = "numerocontacto") String numerocontacto,
            @RequestParam(name = "admin") String admin,
            @RequestParam(name = "accion") String accion,
            @RequestParam(name = "dia") String dia) {
        if (accion.equals("Borrar")) {
            rt.deleteById(iddoctor);
        } else if (accion.equals("Editar")) {
            Turno editado = rt.findById(iddoctor).get();
            editado.setIddoctor(iddoctor);
            editado.setHorario(horario);
            editado.setnombrepropietario(nombrepropietario);
            editado.setNombremascota(nombremascota);
            editado.setNumerocontacto(numerocontacto);
            editado.setTipoanimal(tipoanimal);
            editado.setDia(dia);
            rt.saveAndFlush(editado);
        }
        Usuario usuario;
        usuario = ru.findById(admin).get();
        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#turnos";
    }

}
