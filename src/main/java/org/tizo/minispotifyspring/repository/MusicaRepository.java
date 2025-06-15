package org.tizo.minispotifyspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tizo.minispotifyspring.model.Genero;
import org.tizo.minispotifyspring.model.Musica;

import java.util.List;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Integer> {
    List<Musica> findAllByArtistaContains(String artista);
    List<Musica> findAllByTituloContains(String titulo);
    List<Musica> findAllByGenero(Genero genero);
}
