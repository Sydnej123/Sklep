package pl.InternetowySklepMuzyczny.sklep.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.InternetowySklepMuzyczny.sklep.models.CompositePrimaryKeySzcze_zam;
import pl.InternetowySklepMuzyczny.sklep.models.Szczegoly_zamowienia;
import pl.InternetowySklepMuzyczny.sklep.repositories.Szczegoly_zamowieniaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Szczegoly_zamowieniaServiceImp implements Szczegoly_zamowieniaService {
    @Autowired
    Szczegoly_zamowieniaRepository szczegoly_zamowieniaRepository;
    @Override
    public List<Szczegoly_zamowienia> findById(CompositePrimaryKeySzcze_zam compositePrimaryKeySzcze_zam) {
        return szczegoly_zamowieniaRepository.findById(compositePrimaryKeySzcze_zam).stream().collect(Collectors.toList());
    }

    @Override
    public List<Szczegoly_zamowienia> findByZamowienieId(Integer id) {
        return szczegoly_zamowieniaRepository.findByZamowienieId(id);
    }
}