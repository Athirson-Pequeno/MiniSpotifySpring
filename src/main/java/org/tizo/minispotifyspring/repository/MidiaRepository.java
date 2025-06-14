package org.tizo.minispotifyspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tizo.minispotifyspring.model.Midia;

@Repository
public interface MidiaRepository extends JpaRepository<Midia, Integer> {
    Midia getMidiaById(Integer midiaId);
}
