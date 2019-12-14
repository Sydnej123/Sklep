package pl.InternetowySklepMuzyczny.sklep.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.InternetowySklepMuzyczny.sklep.models.Utwor;
import pl.InternetowySklepMuzyczny.sklep.repositories.UtworRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class UtworServiceImp implements  UtworService {
    @Autowired
    UtworRepository utworRepository;
    @Override
    public List<Utwor> findAll() {
        List<Utwor> temp = new ArrayList<>();
        for (Utwor utwor: utworRepository.findAll()){
            temp.add(utwor);
        }
        return temp;
    }

    @Override
    public void save(Utwor utwor) {
        utworRepository.save(utwor);
    }

    @Override
    public void delete(Utwor utwor) {
        utworRepository.delete(utwor);
    }
}
