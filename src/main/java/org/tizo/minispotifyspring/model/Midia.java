package org.tizo.minispotifyspring.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Midia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String artista;
    private Integer duracao;

}
