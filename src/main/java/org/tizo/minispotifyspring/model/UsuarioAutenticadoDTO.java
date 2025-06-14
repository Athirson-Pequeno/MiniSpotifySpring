package org.tizo.minispotifyspring.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioAutenticadoDTO(
        @Email(message = "O e-mail deve ser válido")
        @NotBlank(message = "O e-mail é obrigatório")
        String email,

        @NotBlank(message = "Token é obrigatório")
        String token

) {}