package org.tizo.minispotifyspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tizo.minispotifyspring.model.Midia;
import org.tizo.minispotifyspring.model.Playlist;
import org.tizo.minispotifyspring.model.PlaylistDTO;
import org.tizo.minispotifyspring.model.Usuario;
import org.tizo.minispotifyspring.repository.MidiaRepository;
import org.tizo.minispotifyspring.repository.PlaylistRepository;

import java.util.List;

@Service
public class PlaylistService {


    private final PlaylistRepository playlistRepository;
    private final MidiaRepository midiaRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository, MidiaRepository midiaRepository) {
        this.playlistRepository = playlistRepository;
        this.midiaRepository = midiaRepository;
    }

    public List<PlaylistDTO> findByUsuario(Usuario usuario) {
        return playlistRepository.findAllByUsuario(usuario).stream()
                .map(playlist -> new PlaylistDTO(
                        playlist.getId(),
                        playlist.getUsuario().getNome(),
                        playlist.getTitulo(),
                        playlist.getMidias()
                )).toList();
    }

    public PlaylistDTO createPlaylist(String titulo, Usuario usuario) {
        Playlist playlist = new Playlist();
        playlist.setTitulo(titulo);
        playlist.setUsuario(usuario);

        playlistRepository.save(playlist);
        return new PlaylistDTO(playlist.getId(), playlist.getUsuario().getNome(), playlist.getTitulo(), playlist.getMidias());
    }

    public Playlist getPlaylistById(Integer id) {

        return playlistRepository.findPlaylistById(id);
    }

    public boolean deletePlaylistById(Integer playlistId, Usuario usuario) {
        Playlist playlist = getPlaylistById(playlistId);

        if (playlist != null) {
            if (playlist.getUsuario().equals(usuario)) {
                playlistRepository.deleteById(playlistId);
                return true;
            }
        }
        return false;
    }

    public Playlist addMidia(Integer midiaId, Usuario usuario, Integer playlistId) {
        Playlist playlist = getPlaylistById(playlistId);

        if (playlist != null) {
            if (playlist.getUsuario().equals(usuario)) {
                Midia midia = midiaRepository.getMidiaById(midiaId);
                if (midia != null) {
                    playlist.getMidias().add(midia);
                    return playlistRepository.save(playlist);
                }
            }
        }
        return null;
    }

    public Playlist removeMidia(Integer midiaId, Usuario usuario, Integer playlistId) {
        Playlist playlist = getPlaylistById(playlistId);

        if (playlist != null) {
            if (playlist.getUsuario().equals(usuario)) {
                Midia midia = midiaRepository.getMidiaById(midiaId);
                if (midia != null) {
                    playlist.getMidias().remove(midia);
                    return playlistRepository.save(playlist);
                }
            }
        }
        return null;
    }
}
