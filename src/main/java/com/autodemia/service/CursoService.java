package com.autodemia.service;

import com.autodemia.domain.Curso;
import com.autodemia.domain.Usuario;
import com.autodemia.forms.CursoForm;
import java.util.List;

public interface CursoService {
    List<Curso> findByProfesor(Usuario profesor);
    void guardarCursoConEstructura(CursoForm cursoForm, Usuario profesor);
    List<Curso> findAll();
    Curso findById(Long id);
}
