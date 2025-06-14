package org.tizo.minispotifyspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tizo.minispotifyspring.model.Podcast;
import org.tizo.minispotifyspring.repository.PodCastRepository;

import java.util.List;

@Service
public class PodcastService {

    private final PodCastRepository podcastRepository;

    @Autowired
    public PodcastService(PodCastRepository podcastRepository) {
        this.podcastRepository = podcastRepository;
    }

    public List<Podcast> findAll() {
        return podcastRepository.findAll();
    }

    public Podcast findById(Integer id) {
        return podcastRepository.findById(id).get();
    }

    public Podcast save(Podcast podcast) {
        return podcastRepository.save(podcast);
    }

    public void delete(Podcast podcast) {
        podcastRepository.delete(podcast);
    }

    public void delete(Integer id) {
        podcastRepository.deleteById(id);
    }

    public List<Podcast> findByArtista(String artista) {
        return podcastRepository.findAllByArtistaContains(artista);
    }

    public List<Podcast> findByTitulo(String titulo) {
        return podcastRepository.findAllByTituloContains(titulo);
    }
}
