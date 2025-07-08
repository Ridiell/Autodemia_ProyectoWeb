package com.autodemia.service;
import com.autodemia.domain.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario save(Usuario user);
    public List<Usuario> findAll();
    Optional<Usuario> findByEmail(String email);

}
