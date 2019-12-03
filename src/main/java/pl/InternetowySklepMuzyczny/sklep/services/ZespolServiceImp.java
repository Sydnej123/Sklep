package pl.InternetowySklepMuzyczny.sklep.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.InternetowySklepMuzyczny.sklep.models.Zespol;
import pl.InternetowySklepMuzyczny.sklep.repositories.ZespolRepository;

import java.util.ArrayList;

@Service
public class ZespolServiceImp implements ZespolService {
    @Autowired
    ZespolRepository zespolRepository;
    @Override
    public ArrayList<Zespol> findAll() {
        ArrayList<Zespol> temp = new ArrayList<>();
        for (Zespol zespol:zespolRepository.findAll()) {
            temp.add(zespol);
        }
        return temp;
    }

    @Override
    public void save(Zespol zespol) {
        zespolRepository.save(zespol);
    }


}
