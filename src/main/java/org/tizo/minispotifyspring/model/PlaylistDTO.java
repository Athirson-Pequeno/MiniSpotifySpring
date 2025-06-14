package org.tizo.minispotifyspring.model;

import java.util.List;

public record PlaylistDTO(Integer id, String proprietario, String titulo, List<Midia> midiaIds) {
}
