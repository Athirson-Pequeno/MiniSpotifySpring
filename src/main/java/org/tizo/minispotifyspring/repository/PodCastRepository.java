package org.tizo.minispotifyspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tizo.minispotifyspring.model.Podcast;

import java.util.List;

@Repository
public interface PodCastRepository extends JpaRepository<Podcast, Integer> {
    List<Podcast> findAllByArtistaContains(String artista);
    List<Podcast> findAllByTituloContains(String titulo);
}
