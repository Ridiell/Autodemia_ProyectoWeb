package com.autodemia.controller;

import com.autodemia.dao.UserDao;
import com.autodemia.domain.Curso;
import com.autodemia.service.impl.UserServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.autodemia.domain.Usuario;
import com.autodemia.forms.CursoForm;
import com.autodemia.service.CursoService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.server.ServerWebExchangeExtensionsKt.principal;

@Controller
@RequestMapping("/profesor")
public class ProfesorController {

    @Autowired
    private UserServiceImpl userService;
    
    @Autowired
    private CursoService cursoService;
    
    @GetMapping("/dashboard")
    public String mostrarDashboardProfesor(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername(); // el email es el "username" por cómo mapeaste tu UserDetails
        Usuario profesor = userService.findByEmail(email);
        List<Curso> cursos = cursoService.findByProfesor(profesor);
        model.addAttribute("cursos", cursos);
        return "profesor/dashboard";
    }
    

    @GetMapping("/cursos/nuevo")
    public String nuevoCurso(Model model) {
        model.addAttribute("cursoForm", new CursoForm()); 
        return "profesor/crear_curso";
    }

    @PostMapping("/cursos")
    public String guardarCurso(@ModelAttribute CursoForm cursoForm, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario profesor = userService.findByEmail(userDetails.getUsername());
        cursoService.guardarCursoConEstructura(cursoForm, profesor);
        return "redirect:/profesor/dashboard";
    }
}
