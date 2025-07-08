
package com.autodemia.controller;

import com.autodemia.domain.Role;
import com.autodemia.domain.Usuario;
import com.autodemia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsuarioService userService;

    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/profesores/nuevo")
    public String nuevoProfesorForm(Model model) {
        model.addAttribute("user", new Usuario());
        return "admin/nuevo_profesor"; 
    }

    @PostMapping("/profesores")
    public String crearProfesor(@ModelAttribute Usuario user) {
        user.setRole(Role.PROFESOR);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.save(user);
        return "redirect:/admin/dashboard";
    }
}