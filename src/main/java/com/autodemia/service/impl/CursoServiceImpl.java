package com.autodemia.service.impl;

import com.autodemia.domain.Curso;
import com.autodemia.domain.Modulo;
import com.autodemia.domain.Clase;
import com.autodemia.domain.Usuario;
import com.autodemia.forms.CursoForm;
import com.autodemia.forms.ModuloForm;
import com.autodemia.forms.ClaseForm;
import com.autodemia.dao.CursoDao;
import com.autodemia.dao.ModuloDao;
import com.autodemia.dao.ClaseDao;
import com.autodemia.service.CursoService;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoDao cursoRepo;

    @Autowired
    private ModuloDao moduloRepo;

    @Autowired
    private ClaseDao claseRepo;

    @Override
    public List<Curso> findByProfesor(Usuario profesor) {
        return cursoRepo.findByProfesor(profesor);
    }

    @Override
    public void guardarCursoConEstructura(CursoForm cursoForm, Usuario profesor) {
        Curso curso = new Curso();
        curso.setNombre(cursoForm.getNombre());
        curso.setDescripcion(cursoForm.getDescripcion());
        curso.setProfesor(profesor);

        cursoRepo.save(curso);

        for (ModuloForm moduloForm : cursoForm.getModulos()) {
            Modulo modulo = new Modulo();
            modulo.setNombre(moduloForm.getNombre());
            modulo.setCurso(curso);
            moduloRepo.save(modulo);

            for (ClaseForm claseForm : moduloForm.getClases()) {
                Clase clase = new Clase();
                clase.setTitulo(claseForm.getTitulo());
                clase.setContenido(claseForm.getContenido());
                clase.setModulo(modulo);
                claseRepo.save(clase);
            }
        }
    }
    
    @Override
public List<Curso> findAll() {
    return cursoRepo.findAll();
    
}

@Override
public Curso findById(Long id) {
    return cursoRepo.findById(id).orElse(null);
}
}
