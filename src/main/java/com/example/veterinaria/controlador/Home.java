package com.example.veterinaria.controlador;


import com.example.veterinaria.modelo.repository.RepoProductos;
import com.example.veterinaria.modelo.repository.RepoTurnos;
import com.example.veterinaria.modelo.repository.RepoUsuarios;
import com.example.veterinaria.modelo.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class Home {

    @Autowired
    private RepoUsuarios repositorioUsuarios;

    @Autowired
    private RepoTurnos repositorioTurnos;
    
    @Autowired
    private RepoProductos repositorioProductos;
    

    @PostMapping("/login")
    public String home(Model model,
            @RequestParam(name = "id", required = true) String id,
            @RequestParam(name = "password", required = true) String password) {
        Usuario usuario = null;
        List<Usuario> match = repositorioUsuarios.findByIdAndPassword(id, password);
        if (!match.isEmpty()) {
            usuario = match.get(0);
        }
        return login(model, usuario);
    }

    @GetMapping("/login")
    public String home(Model model) {
        return login(model, (Usuario) model.getAttribute("usuario"));
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
    
        @GetMapping("/turnos/{id}")
    public String resumen(final RedirectAttributes redirectAttributes,
            @PathVariable(value = "id") String vetId) {
        redirectAttributes.addFlashAttribute("usuario", repositorioUsuarios.findById(vetId).get());
        return "redirect:/login#";
    }
    
        private String login(Model model, Usuario usuario) {
        if (usuario != null) {
            
            model.addAttribute("usuario", usuario);
            model.addAttribute("doctores", repositorioUsuarios.findByRol("Veterinario"));
            model.addAttribute("productos", repositorioProductos.findAll());
            model.addAttribute("productosregulares", repositorioProductos.findByCategoria("regulares"));
            model.addAttribute("medicamentos", repositorioProductos.findByCategoria("medicamentos"));
            model.addAttribute("turnos", repositorioTurnos.findAll());
            model.addAttribute("turnosDelDoctor", repositorioTurnos.findByIddoctor(usuario.getId()));
            model.addAttribute("usuarios", repositorioUsuarios.findAll());
            return usuario.getRol().toLowerCase();
        }
        model.addAttribute("error", "No se encontró Nombre/Password");
        return "index";
    }
}
