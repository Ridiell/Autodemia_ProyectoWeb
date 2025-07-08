package com.autodemia.service;
import com.autodemia.domain.Usuario;
import java.util.List;

public interface UsuarioService {
    Usuario save(Usuario user);
    Usuario findByEmail(String email);
    public List<Usuario> findAll();
}
