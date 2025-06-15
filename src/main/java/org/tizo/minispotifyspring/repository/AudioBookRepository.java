package org.tizo.minispotifyspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tizo.minispotifyspring.model.AudioBook;

import java.util.List;

@Repository
public interface AudioBookRepository extends JpaRepository<AudioBook, Integer> {
    List<AudioBook> findAllByArtistaContains(String artista);
    List<AudioBook> findAllByTituloContains(String titulo);
}
