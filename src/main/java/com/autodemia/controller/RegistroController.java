package com.autodemia.controller;

import com.autodemia.domain.Role;
import com.autodemia.domain.Usuario;
import com.autodemia.service.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistroController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    public RegistroController(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registro")
    public String registroForm(Model model) {
        model.addAttribute("user", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(@ModelAttribute Usuario user) {
        user.setRole(Role.ESTUDIANTE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usuarioService.save(user);
        return "redirect:/login?success";
    }
}
