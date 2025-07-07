package com.autodemia.service;
import com.autodemia.domain.Usuario;
import java.util.List;

public interface UsuarioService {
    Usuario save(Usuario user);
    public List<Usuario> findAll();
}
