package pl.InternetowySklepMuzyczny.sklep.services;

import pl.InternetowySklepMuzyczny.sklep.models.CompositePrimaryKeySzcze_zam;
import pl.InternetowySklepMuzyczny.sklep.models.Szczegoly_zamowienia;

import java.util.List;

public interface Szczegoly_zamowieniaService {
    Szczegoly_zamowienia findById(CompositePrimaryKeySzcze_zam compositePrimaryKeySzcze_zam);
    List<Szczegoly_zamowienia> findByZamowienieId(Integer id);
}
