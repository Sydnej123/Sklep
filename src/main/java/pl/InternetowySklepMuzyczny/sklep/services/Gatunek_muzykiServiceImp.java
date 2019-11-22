package pl.InternetowySklepMuzyczny.sklep.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.InternetowySklepMuzyczny.sklep.models.Gatunek_muzyki;
import pl.InternetowySklepMuzyczny.sklep.repositories.Gatunek_muzykiRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class Gatunek_muzykiServiceImp implements  Gatunek_muzykiService{
    @Autowired
    private Gatunek_muzykiRepository gatunek_muzykiRepository;
    @Override
    public List<Gatunek_muzyki> findAllGatunek() {
        List<Gatunek_muzyki> result = new ArrayList<>();
        for(Gatunek_muzyki gatunek_muzyki: gatunek_muzykiRepository.findAll()){
            result.add(gatunek_muzyki);
        }
        return result;
    }
    public void check(){
        System.out.println("Dziala");
    }
}
