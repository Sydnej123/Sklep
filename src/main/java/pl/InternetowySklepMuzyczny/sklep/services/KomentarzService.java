package pl.InternetowySklepMuzyczny.sklep.services;

import pl.InternetowySklepMuzyczny.sklep.models.Komentarz;

import java.util.List;

public interface KomentarzService {
    List<Komentarz> findAll();
    List<Komentarz> findByAlbumId(Integer id);
    void delete(Komentarz k);
    void save(Komentarz k);
};
