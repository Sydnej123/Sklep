package pl.InternetowySklepMuzyczny.sklep.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.InternetowySklepMuzyczny.sklep.models.Komentarz;
import pl.InternetowySklepMuzyczny.sklep.repositories.KomentarzRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class KomentarzServiceImp implements  KomentarzService{
    @Autowired
    KomentarzRepository komentarzRepository;
    @Override
    public List<Komentarz> findAll() {
        List<Komentarz> temp = new ArrayList<>();
        for(Komentarz komentarz: komentarzRepository.findAll()){
            temp.add(komentarz);
        }
        return temp;
    }
}
