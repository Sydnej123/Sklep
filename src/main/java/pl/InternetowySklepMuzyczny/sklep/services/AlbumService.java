package pl.InternetowySklepMuzyczny.sklep.services;

import pl.InternetowySklepMuzyczny.sklep.models.Album;

import java.util.List;

public interface AlbumService {
    long countGatunek(int gatunek_id);
    List<Album> findAll();
}
