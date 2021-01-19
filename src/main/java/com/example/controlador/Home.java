package com.example.controlador;


import com.example.modelo.RepoUsuarios;
import com.example.modelo.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class Home {

    @Autowired
    private RepoUsuarios ru;

    
    
    private String login(Model model, Usuario usuario) {
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
    
    
}
