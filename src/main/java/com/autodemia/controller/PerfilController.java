package com.autodemia.controller;

import com.autodemia.domain.Usuario;
import com.autodemia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private UsuarioService userService;

    private static final String UPLOAD_DIR = "src/main/resources/static/images/perfiles/";

    @GetMapping
    public String mostrarPerfil(Model model, Authentication auth) {
        String email = auth.getName();
        Optional<Usuario> usuarioOpt = userService.findByEmail(email);

        if (usuarioOpt.isPresent()) {
            model.addAttribute("usuario", usuarioOpt.get());
            return "perfil/perfil";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/actualizar")
    public String actualizarPerfil(@ModelAttribute Usuario formUser,
                                   @RequestParam("fotoArchivo") MultipartFile fotoArchivo,
                                   @RequestParam("fotoUrl") String fotoUrl,
                                   Authentication auth) {

        String email = auth.getName();
        Optional<Usuario> usuarioOpt = userService.findByEmail(email);

        if (usuarioOpt.isPresent()) {
            Usuario user = usuarioOpt.get();

            // Actualizar campos editables
            user.setName(formUser.getName());
            user.setEmail(formUser.getEmail());

            if (formUser.getPassword() != null && !formUser.getPassword().isBlank()) {
                user.setPassword(new BCryptPasswordEncoder().encode(formUser.getPassword()));
            }

            //foto perfil imagen
            try {
                if (!fotoArchivo.isEmpty()) {
                    String nombreArchivo = UUID.randomUUID() + "_" + fotoArchivo.getOriginalFilename();
                    File archivoDestino = new File(UPLOAD_DIR + nombreArchivo);
                    archivoDestino.getParentFile().mkdirs();
                    fotoArchivo.transferTo(archivoDestino);

                    // Guardar ruta relativa
                    user.setFotoUrl("/images/perfiles/" + nombreArchivo);
                } else if (fotoUrl != null && !fotoUrl.isBlank()) {
                    user.setFotoUrl(fotoUrl);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Mantener el rol original no editable 
            user.setRole(user.getRole());

            userService.save(user);
            return "redirect:/perfil?success";
        } else {
            return "redirect:/login";
        }
    }
}