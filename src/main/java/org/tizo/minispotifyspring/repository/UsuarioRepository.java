package org.tizo.minispotifyspring.repository;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tizo.minispotifyspring.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmailAndSenha(@NotBlank String email, @NotBlank String senha);

    Usuario findByEmail(@NotBlank String email);
}
