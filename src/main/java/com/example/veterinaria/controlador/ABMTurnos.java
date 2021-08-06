package com.example.veterinaria.controlador;

import com.example.veterinaria.modelo.repository.RepoTurnos;
import com.example.veterinaria.modelo.repository.RepoUsuarios;
import com.example.veterinaria.modelo.Usuario;
import com.example.veterinaria.modelo.Turno;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
                && doctor.getManejoanimal().contains(tipoanimal) && rt.findByDiaAndHorario(dia, horario).isEmpty()) {
            rt.saveAndFlush(turno);
        } else if (!doctor.getManejoanimal().contains(tipoanimal)) {
            redirectAttributes.addFlashAttribute("errormsg", "No se puede asignar el turno al veterinario " + doctor.getNombre() + " porque no sabe operar " + tipoanimal);
            return "redirect:/error";
        } else if (!doctor.getDiaslaborales().contains(dia)) {
            redirectAttributes.addFlashAttribute("errormsg", "No se puede asignar el turno al veterinario " + doctor.getNombre() + " porque no trabaja los " + dia);
            return "redirect:/error";
        } else if (!rt.findByDiaAndHorario(dia, horario).isEmpty()) {
            redirectAttributes.addFlashAttribute("errormsg", "No se puede asignar el turno al veterinario " + doctor.getNombre() + " porque ya hay un turno asignado el día " + dia
                    + " a las " + horario);
            return "redirect:/error";
        }

        Usuario usuario;
        usuario = ru.findById(admin).get();
        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#turnos";
    }

    @PostMapping("/editar-borrar-turno")
    public String editarBorrarTurno(final RedirectAttributes redirectAttributes,
            @RequestParam(name = "iddoctor") String iddoctor,
            @RequestParam(name = "horario") String horario,
            @RequestParam(name = "nombrepropietario") String nombrepropietario,
            @RequestParam(name = "nombremascota") String nombremascota,
            @RequestParam(name = "tipoanimal") String tipoanimal,
            @RequestParam(name = "numerocontacto") String numerocontacto,
            @RequestParam(name = "admin") String admin,
            @RequestParam(name = "accion") String accion,
            @RequestParam(name = "dia") String dia,
            @RequestParam(name = "idturno") String idTurno) {

        /*Turno turno = new Turno(iddoctor, horario, nombrepropietario,
                nombremascota, tipoanimal, numerocontacto, dia);*/
        if (accion.equals("Borrar")) {           
            rt.deleteById(idTurno);
        } else if (accion.equals("Editar")) {
            Usuario doctor = ru.findById(iddoctor).get();
            Turno editado = rt.findById(idTurno).get();
            editado.setIddoctor(iddoctor);
            editado.setHorario(horario);
            editado.setnombrepropietario(nombrepropietario);
            editado.setNombremascota(nombremascota);
            editado.setNumerocontacto(numerocontacto);
            editado.setTipoanimal(tipoanimal);
            editado.setDia(dia);
            if (doctor.getManejoanimal().contains(tipoanimal)) {
                if (doctor.getDiaslaborales().contains(dia)) {
                    if (rt.findByDiaAndHorarioAndIddoctor(dia, horario, iddoctor).isEmpty()) {
                        rt.saveAndFlush(editado);
                    } else {
                        redirectAttributes.addFlashAttribute("errormsg", "No se pudo realizar el cambio, El doctor tiene un turno en ese horario");
                        return "redirect:/error";
                    }
                } else {
                    redirectAttributes.addFlashAttribute("errormsg", "No se pudo realizar el cambio, El doctor no trabaja ese día");
                    return "redirect:/error";

                }
            } else {
                redirectAttributes.addFlashAttribute("errormsg", "No se pudo realizar el cambio, El doctor no maneja esa clase de animal");
                return "redirect:/error";
            }
        }
        Usuario usuario;
        usuario = ru.findById(admin).get();
        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#turnos";
    }

    @GetMapping("/volver")
    public String logout() {
        return "redirect:/";
    }

}
