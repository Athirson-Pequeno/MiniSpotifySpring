package org.tizo.minispotifyspring.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(

        @NotBlank(message = "A senha é obrigatória")
        String senha,

        @Email(message = "O e-mail deve ser válido")
        @NotBlank(message = "O e-mail é obrigatório")
        String email

) {}