/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.autodemia.service;

import com.autodemia.domain.CursoEstudiante;
import com.autodemia.domain.Usuario;
import java.util.List;

public interface CursoEstudianteService {
    List<CursoEstudiante> findByEstudiante(Usuario estudiante);
    void inscribirEstudianteEnCurso(Long cursoId, Usuario estudiante);
}
