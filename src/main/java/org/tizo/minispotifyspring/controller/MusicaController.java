package org.tizo.minispotifyspring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tizo.minispotifyspring.model.Genero;
import org.tizo.minispotifyspring.model.Musica;
import org.tizo.minispotifyspring.service.MusicaService;

import java.util.List;

@RestController
@RequestMapping("/musica")
public class MusicaController {

    private final MusicaService musicaService;

    @Autowired
    public MusicaController(MusicaService musicaService) {
        this.musicaService = musicaService;
    }

    @GetMapping
    public List<Musica> getMusica() {
        return musicaService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Musica getMusicaById(@PathVariable Integer id) {
        return musicaService.findById(id);
    }

    @GetMapping(value = "/buscar/artista")
    public List<Musica> getMusicaByNome(@RequestParam String artista) {
        return musicaService.findByArtista(artista);
    }

    @GetMapping(value = "/buscar/titulo")
    public List<Musica> getMusicaByTitulo(@RequestParam String titulo) {
        return musicaService.findByTitulo(titulo);
    }

    @GetMapping(value = "/buscar/genero")
    public List<Musica> getMusicaByGenero(@RequestParam Genero genero) {
        return musicaService.findByGenero(genero);
    }

    @PostMapping
    public Musica addMusica(@RequestBody Musica musica) {
        return musicaService.save(musica);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteMusicaById(@PathVariable Integer id) {
        musicaService.delete(id);
    }
}
