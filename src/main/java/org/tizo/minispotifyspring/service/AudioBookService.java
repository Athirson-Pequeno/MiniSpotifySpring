package org.tizo.minispotifyspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tizo.minispotifyspring.model.AudioBook;
import org.tizo.minispotifyspring.repository.AudioBookRepository;
import org.tizo.minispotifyspring.repository.PodCastRepository;

import java.util.List;

@Service
public class AudioBookService {

    private final AudioBookRepository audioBookRepository;

    @Autowired
    public AudioBookService(AudioBookRepository audioBookRepository) {
        this.audioBookRepository = audioBookRepository;
    }

    public List<AudioBook> findAll() {
        return audioBookRepository.findAll();
    }

    public AudioBook save(AudioBook audioBook) {
        return audioBookRepository.save(audioBook);
    }

    public AudioBook findById(Integer id) {
        return audioBookRepository.findById(id).orElseThrow(() -> new RuntimeException("AudioBook não encontrado"));
    }

    public void delete(AudioBook audioBook) {
        audioBookRepository.delete(audioBook);
    }

    public void delete(Integer id) {
        this.delete(findById(id));
    }

    public List<AudioBook> findByArtista(String artista) {
        return audioBookRepository.findAllByArtistaContains(artista);
    }

    public List<AudioBook> findByTitulo(String titulo) {
        return audioBookRepository.findAllByTituloContains(titulo);
    }
}
