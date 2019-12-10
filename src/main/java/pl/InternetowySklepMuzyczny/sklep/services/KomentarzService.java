package pl.InternetowySklepMuzyczny.sklep.services;

import pl.InternetowySklepMuzyczny.sklep.models.Komentarz;

import java.util.List;

public interface KomentarzService {
    List<Komentarz> findAll();
};
