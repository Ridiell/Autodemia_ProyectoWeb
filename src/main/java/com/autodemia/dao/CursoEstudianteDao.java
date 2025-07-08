
package com.autodemia.dao;

import com.autodemia.domain.CursoEstudiante;
import com.autodemia.domain.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoEstudianteDao extends JpaRepository<CursoEstudiante, Long> {
    List<CursoEstudiante> findByEstudiante(Usuario estudiante);
}
