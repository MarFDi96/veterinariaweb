package com.example.veterinaria.controlador;


import com.example.veterinaria.modelo.RepoUsuarios;
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
    private RepoUsuarios ru;

    
    
    private String login(Model model, Usuario usuario, Object o) {
        if (usuario != null) {
            
            model.addAttribute("usuario", usuario);
            model.addAttribute("usuarios", ru.findAll());
            return usuario.getRol().toLowerCase();
        }
        model.addAttribute("error", "No se encontró Nombre/Password");
        return "index";
    }

    @PostMapping("/login")
    public String home(Model model,
            @RequestParam(name = "id", required = true) String id,
            @RequestParam(name = "password", required = true) String password) {
        Usuario usuario = null;
        List<Usuario> match = ru.findByIdAndPassword(id, password);
        if (!match.isEmpty()) {
            usuario = match.get(0);
        }
        return login(model, usuario, null);
    }

    @GetMapping("/login")
    public String home(Model model) {
        return login(model, (Usuario) model.getAttribute("usuario"), null);
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
    
        @GetMapping("/resumen/{codigo}")
    public String resumen(final RedirectAttributes redirectAttributes,
            @PathVariable(value = "codigo") String adminId) {
        redirectAttributes.addFlashAttribute("usuario", ru.findById(adminId).get());
        return "redirect:/login#";
    }
    
}
