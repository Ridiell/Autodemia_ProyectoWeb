
package com.autodemia.dao;

import com.autodemia.domain.Curso;
import com.autodemia.domain.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoDao extends JpaRepository<Curso, Long>{
    List<Curso> findByProfesor(Usuario profesor);
}
