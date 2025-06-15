package org.tizo.minispotifyspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tizo.minispotifyspring.model.Genero;
import org.tizo.minispotifyspring.model.Musica;
import org.tizo.minispotifyspring.repository.MusicaRepository;

import java.util.List;

@Service
public class MusicaService {

    private final MusicaRepository musicaRepository;

    @Autowired
    public MusicaService(MusicaRepository musicaRepository) {
        this.musicaRepository = musicaRepository;
    }

    public List<Musica> findAll() {
        return musicaRepository.findAll();
    }

    public Musica findById(Integer id) {
        return musicaRepository.findById(id).orElseThrow(() -> new RuntimeException("Música não encontrada"));
    }

    public Musica save(Musica musica) {
        return musicaRepository.save(musica);
    }

    public void delete(Musica musica) {
        musicaRepository.delete(musica);
    }

    public void delete(Integer id) {
        musicaRepository.deleteById(id);
    }

    public List<Musica> findByArtista(String artista) {
        return musicaRepository.findAllByArtistaContains(artista);
    }

    public List<Musica> findByTitulo(String titulo) {
        return musicaRepository.findAllByTituloContains(titulo);
    }

    public List<Musica> findByGenero(Genero genero) {
        return musicaRepository.findAllByGenero(genero);
    }
}
