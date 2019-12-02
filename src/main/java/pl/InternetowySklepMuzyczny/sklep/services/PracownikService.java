package pl.InternetowySklepMuzyczny.sklep.services;

import pl.InternetowySklepMuzyczny.sklep.models.Pracownik;

import java.util.List;

public interface PracownikService {
    List<Pracownik> getPracownikByID(Integer id);
}
