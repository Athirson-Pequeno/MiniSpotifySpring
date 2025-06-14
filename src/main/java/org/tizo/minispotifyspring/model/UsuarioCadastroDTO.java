package org.tizo.minispotifyspring.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioCadastroDTO(

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @Email(message = "O e-mail deve ser válido")
        @NotBlank(message = "O e-mail é obrigatório")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        String senha

) {}