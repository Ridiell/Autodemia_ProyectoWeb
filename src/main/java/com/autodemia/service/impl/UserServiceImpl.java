
package com.autodemia.service.impl;
import com.autodemia.dao.UserDao;
import com.autodemia.domain.Usuario;
import com.autodemia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UsuarioService {

    @Autowired
    private UserDao userRepository;

    @Override
    public Usuario save(Usuario user) {
        return userRepository.save(user);
    }

    @Override
    public List<Usuario> findAll() {
        return userRepository.findAll();
    }
}