package org.tizo.minispotifyspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tizo.minispotifyspring.model.LoginDTO;
import org.tizo.minispotifyspring.model.Usuario;
import org.tizo.minispotifyspring.model.UsuarioAutenticadoDTO;
import org.tizo.minispotifyspring.model.UsuarioCadastroDTO;
import org.tizo.minispotifyspring.repository.UsuarioRepository;
import org.tizo.minispotifyspring.util.JwtUtil;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UsuarioAutenticadoDTO salvar(UsuarioCadastroDTO usuario) {
        Usuario usuarioSalvo = new Usuario();

        if (usuarioRepository.findByEmail(usuario.email()) != null) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        usuarioSalvo.setNome(usuario.nome());
        usuarioSalvo.setEmail(usuario.email());
        usuarioSalvo.setSenha(passwordEncoder.encode(usuario.senha()));

        usuarioRepository.save(usuarioSalvo);

        LoginDTO loginDTO = new LoginDTO(usuario.senha(), usuario.email());

        return autenticar(loginDTO);
    }

    public UsuarioAutenticadoDTO autenticar(LoginDTO loginDTO) {

        Usuario usuario = usuarioRepository.findByEmail(loginDTO.email());

        if (usuario != null && passwordEncoder.matches(loginDTO.senha(), usuario.getSenha())) {
            String jwt = JwtUtil.gerarToken(usuario.getEmail());
            return new UsuarioAutenticadoDTO(usuario.getEmail(), jwt);
        }
        return null;
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
