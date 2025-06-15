package org.tizo.minispotifyspring.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    @JoinTable(
            name = "tb_midia_playlist",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "midia_id"))
    private List<Midia> midias = new ArrayList<>();
    private String titulo;

    @ManyToOne
    private Usuario usuario;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Playlist playlist)) return false;
        return Objects.equals(id, playlist.id) && Objects.equals(midias, playlist.midias) && Objects.equals(titulo, playlist.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, midias, titulo);
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "titulo='" + titulo + '\'' +
                ", midias=" + midias +
                ", id=" + id +
                '}';
    }
}
