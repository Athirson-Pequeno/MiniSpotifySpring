package org.tizo.minispotifyspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tizo.minispotifyspring.model.Playlist;
import org.tizo.minispotifyspring.model.Usuario;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
    List<Playlist> findAllByUsuario(Usuario usuario);
}
