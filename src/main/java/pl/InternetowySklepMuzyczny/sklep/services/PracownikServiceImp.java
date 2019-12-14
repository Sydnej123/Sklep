package pl.InternetowySklepMuzyczny.sklep.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.InternetowySklepMuzyczny.sklep.models.Pracownik;
import pl.InternetowySklepMuzyczny.sklep.repositories.PracownikRepository;

import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public String getBestSeller(Date dateFrom, Date dateTo) {
        return pracownikRepository.getBestSeller(dateFrom, dateTo).isEmpty()?"Błąd":pracownikRepository.getBestSeller(dateFrom, dateTo).get(0);
    }

    @Override
    public void delete(Pracownik p) {
        pracownikRepository.delete(p);
    }
}
