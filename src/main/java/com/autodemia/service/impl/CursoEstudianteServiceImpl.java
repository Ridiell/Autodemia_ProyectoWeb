/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.autodemia.service.impl;

import com.autodemia.dao.CursoDao;
import com.autodemia.dao.CursoEstudianteDao;
import com.autodemia.domain.Curso;
import com.autodemia.domain.CursoEstudiante;
import com.autodemia.domain.Usuario;
import com.autodemia.service.CursoEstudianteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoEstudianteServiceImpl implements CursoEstudianteService {

    @Autowired
    private CursoEstudianteDao cursoEstudianteDao;

    @Autowired
    private CursoDao cursoDao;

    @Override
    public List<CursoEstudiante> findByEstudiante(Usuario estudiante) {
        return cursoEstudianteDao.findByEstudiante(estudiante);
    }

    @Override
    public void inscribirEstudianteEnCurso(Long cursoId, Usuario estudiante) {
        Curso curso = cursoDao.findById(cursoId).orElseThrow();
        CursoEstudiante ce = new CursoEstudiante();
        ce.setCurso(curso);
        ce.setEstudiante(estudiante);
        cursoEstudianteDao.save(ce);
    }
}
