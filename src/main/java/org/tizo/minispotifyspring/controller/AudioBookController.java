package org.tizo.minispotifyspring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tizo.minispotifyspring.model.AudioBook;
import org.tizo.minispotifyspring.service.AudioBookService;

import java.util.List;

@RestController
@RequestMapping("/audiobook")
public class AudioBookController {

    private final AudioBookService audioBookService;

    @Autowired
    public AudioBookController(AudioBookService audioBookService) {
        this.audioBookService = audioBookService;
    }

    @GetMapping
    public List<AudioBook> getAudioBook() {
        return audioBookService.findAll();
    }

    @GetMapping(value = "/{id}")
    public AudioBook getAudioBookById(@PathVariable Integer id) {
        return audioBookService.findById(id);
    }

    @GetMapping(value = "/buscar/artista")
    public List<AudioBook> getAudioBookByNome(@RequestParam String artista) {
        return audioBookService.findByArtista(artista);
    }

    @GetMapping(value = "/buscar/titulo")
    public List<AudioBook> getAudioBookByTitulo(@RequestParam String titulo) {
        return audioBookService.findByTitulo(titulo);
    }

    @PostMapping
    public AudioBook addAudioBook(@RequestBody AudioBook audioBook) {
        return audioBookService.save(audioBook);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAudioBookById(@PathVariable Integer id) {
        audioBookService.delete(id);
    }
}
