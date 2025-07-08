/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.autodemia.controller;

import com.autodemia.domain.Curso;
import com.autodemia.domain.CursoEstudiante;
import com.autodemia.domain.Usuario;
import com.autodemia.service.CursoService;
import com.autodemia.service.CursoEstudianteService;
import com.autodemia.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CursoEstudianteService cursoEstudianteService;

    @GetMapping("/cursos")
    public String verCursosDisponibles(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario estudiante = usuarioService.findByEmail(userDetails.getUsername());
        List<Curso> cursos = cursoService.findAll(); // Muestra todos los cursos

        model.addAttribute("cursos", cursos);
        return "estudiante/index";

    }

    @PostMapping("/inscribirse/{cursoId}")
    public String inscribirse(@PathVariable Long cursoId, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario estudiante = usuarioService.findByEmail(userDetails.getUsername());
        Curso curso = cursoService.findById(cursoId);
        cursoEstudianteService.inscribirEstudianteEnCurso(curso.getId(), estudiante);
        return "redirect:/estudiante/cursos?inscrito";
    }

    @GetMapping("/mis-cursos")
    public String verCursosInscritos(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario estudiante = usuarioService.findByEmail(userDetails.getUsername());
        List<CursoEstudiante> cursosInscritos = cursoEstudianteService.findByEstudiante(estudiante);

        model.addAttribute("cursosInscritos", cursosInscritos);
        return "estudiante/mis_cursos"; // Vista nueva
    }
}
