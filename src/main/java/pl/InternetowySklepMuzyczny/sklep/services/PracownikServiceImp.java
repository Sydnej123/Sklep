package pl.InternetowySklepMuzyczny.sklep.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.InternetowySklepMuzyczny.sklep.models.Pracownik;
import pl.InternetowySklepMuzyczny.sklep.repositories.PracownikRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class PracownikServiceImp implements PracownikService {
    @Autowired
    PracownikRepository pracownikRepository;
    @Override
    public List<Pracownik> getPracownikByID(Integer id) {
        return pracownikRepository.getPracownikByID(id);
    }

    @Override
    public void save(Pracownik pracownik) {
        pracownikRepository.save(pracownik);
    }

    @Override
    public List<Pracownik> findAll() {
        ArrayList<Pracownik> temp = new ArrayList<>();
        for(Pracownik pracownik: pracownikRepository.findAll()){
            temp.add(pracownik);
        }
        return temp;
    }
}
