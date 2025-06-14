package org.tizo.minispotifyspring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tizo.minispotifyspring.model.Podcast;
import org.tizo.minispotifyspring.service.PodcastService;

import java.util.List;

@RestController
@RequestMapping("/podcast")
public class PodcastController {

    private final PodcastService podcastService;

    @Autowired
    public PodcastController(PodcastService podcastService) {
        this.podcastService = podcastService;
    }

    @GetMapping
    public List<Podcast> getPodcast() {
        return podcastService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Podcast getPodcastById(@PathVariable Integer id) {
        return podcastService.findById(id);
    }

    @GetMapping(value = "/buscar/artista")
    public List<Podcast> getPodcastByNome(@RequestParam String artista) {
        return podcastService.findByArtista(artista);
    }

    @GetMapping(value = "/buscar/titulo")
    public List<Podcast> getPodcastByTitulo(@RequestParam String titulo) {
        return podcastService.findByTitulo(titulo);
    }

    @PostMapping
    public Podcast addPodcast(@RequestBody Podcast podcast) {
        return podcastService.save(podcast);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePodcastById(@PathVariable Integer id) {
        podcastService.delete(id);
    }
}
