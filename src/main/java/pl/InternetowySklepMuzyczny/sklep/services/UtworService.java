package pl.InternetowySklepMuzyczny.sklep.services;

import pl.InternetowySklepMuzyczny.sklep.models.Utwor;

import java.util.List;

public interface UtworService {
    List<Utwor> findAll();
    void save(Utwor utwor);
}
