package org.tizo.minispotifyspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.tizo.minispotifyspring.model.Playlist;
import org.tizo.minispotifyspring.model.PlaylistDTO;
import org.tizo.minispotifyspring.model.TituloPlaylistDTO;
import org.tizo.minispotifyspring.model.Usuario;
import org.tizo.minispotifyspring.service.PlaylistService;
import org.tizo.minispotifyspring.service.UsuarioService;
import org.tizo.minispotifyspring.util.JwtUtil;

import java.util.List;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    private final PlaylistService playlistService;
    private final UsuarioService usuarioService;


    @Autowired
    public PlaylistController(PlaylistService playlistService, UsuarioService usuarioService) {
        this.playlistService = playlistService;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public PlaylistDTO createPlaylist(@RequestBody TituloPlaylistDTO tituloPlaylistDTO, @RequestHeader("Authorization") String authHeader) {
        return playlistService.createPlaylist(tituloPlaylistDTO.titulo(), buscarUsuarioPorToken(authHeader));
    }

    @GetMapping()
    public List<PlaylistDTO> getPlaylists(@RequestHeader("Authorization") String authHeader) {
        return playlistService.findByUsuario(buscarUsuarioPorToken(authHeader));
    }

    @PutMapping("/add/{playlistID}")
    public PlaylistDTO updatePlaylist(@RequestHeader("Authorization") String authHeader,
                                      @PathVariable Integer playlistID,
                                      @RequestParam Integer midiaID) {

        Usuario usuario = buscarUsuarioPorToken(authHeader);
        Playlist playlist = playlistService.addMidia(midiaID, usuario, playlistID);

        return new PlaylistDTO(playlist.getId(), playlist.getUsuario().getNome(), playlist.getTitulo(), playlist.getMidias());
    }

    @DeleteMapping("/remove/{playlistID}")
    public PlaylistDTO removerMusica(@RequestHeader("Authorization") String authHeader,
                                     @PathVariable Integer playlistID,
                                     @RequestParam Integer midiaID) {

        Usuario usuario = buscarUsuarioPorToken(authHeader);
        Playlist playlist = playlistService.removeMidia(midiaID, usuario, playlistID);

        return new PlaylistDTO(playlist.getId(), playlist.getUsuario().getNome(), playlist.getTitulo(), playlist.getMidias());
    }

    @DeleteMapping("/{playlistID}")
    public ResponseEntity<?> removerPlaylist(@RequestHeader("Authorization") String authHeader,
                                             @PathVariable Integer playlistID) {

        Usuario usuario = buscarUsuarioPorToken(authHeader);
        boolean sucesso =  playlistService.deletePlaylistById(playlistID, usuario);

        return sucesso ? ResponseEntity.status(HttpStatus.OK).body("Playlist removida") : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao remover playlist");
    }


    private Usuario buscarUsuarioPorToken(String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        if (!JwtUtil.tokenValido(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token inválido");
        }
        return usuarioService.buscarPorEmail(JwtUtil.getEmailDoToken(token));
    }
}
