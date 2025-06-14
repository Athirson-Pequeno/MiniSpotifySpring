package org.tizo.minispotifyspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tizo.minispotifyspring.model.LoginDTO;
import org.tizo.minispotifyspring.model.UsuarioAutenticadoDTO;
import org.tizo.minispotifyspring.model.UsuarioCadastroDTO;
import org.tizo.minispotifyspring.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastro")
    public UsuarioAutenticadoDTO cadastrarUsuario(@RequestBody UsuarioCadastroDTO usuarioCadastroDTO) {
        return usuarioService.salvar(usuarioCadastroDTO);
    }

    @PostMapping("/login")
    public UsuarioAutenticadoDTO login(@RequestBody LoginDTO loginDTO) {
        return usuarioService.autenticar(loginDTO);
    }
}
