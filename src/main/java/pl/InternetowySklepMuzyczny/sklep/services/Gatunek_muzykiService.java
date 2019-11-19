package pl.InternetowySklepMuzyczny.sklep.services;

import pl.InternetowySklepMuzyczny.sklep.models.Gatunek_muzyki;

import java.util.List;

public interface Gatunek_muzykiService{
    List<Gatunek_muzyki> findAllGatunek();
}
