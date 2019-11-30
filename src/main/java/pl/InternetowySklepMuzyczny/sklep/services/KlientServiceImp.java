package pl.InternetowySklepMuzyczny.sklep.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.InternetowySklepMuzyczny.sklep.models.Klient;
import pl.InternetowySklepMuzyczny.sklep.repositories.KlientRepository;

import java.util.ArrayList;
@Service
public class KlientServiceImp implements KlientService{

    @Autowired
    private  KlientRepository klientRepository;


    @Override
    public ArrayList<Integer> getClientByUsername(String username) {
        return klientRepository.findByLogin(username);
    }

    @Override
    public ArrayList<String> getPasswordById(Integer id) {
        return klientRepository.getPasswordById(id);
    }

    @Override
    public ArrayList<Integer> findbyEmail(String email) {
        return klientRepository.findbyEmail(email);
    }

    @Override
    public void save(Klient klient) {
        klientRepository.save(klient);
    }


}
