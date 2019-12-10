package pl.InternetowySklepMuzyczny.sklep.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.InternetowySklepMuzyczny.sklep.models.Zamowienie;
import pl.InternetowySklepMuzyczny.sklep.repositories.ZamowienieRepository;

import java.util.List;
@Service
public class ZamowienieServiceImp implements ZamowienieService {
    @Autowired
    ZamowienieRepository zamowienieRepository;
    @Override
    public List<Zamowienie> getOrderByClientId(Integer id) {
        return zamowienieRepository.getOrdersByClientId(id);
    }

    @Override
    public Integer getOrderCountById(Integer id) {
        return zamowienieRepository.getOrderCountById(id);
    }

    @Override
    public List<Zamowienie> getOrderByStatus(Integer status) {
        return zamowienieRepository.getOrdersByStatus(status);
    }

    @Override
    public void save(Zamowienie zamowienie) {
        zamowienieRepository.save(zamowienie);
    }

    @Override
    public List<Zamowienie> getFilteredOrdersDone(String pracownik_id, String klient_id, String zamowienie_wartoscMin, String zamowienie_wartoscMax, String dateMin, String dateMax) {
        if(pracownik_id == null){
            pracownik_id = "pracownik_id";
        }
        if(klient_id == null){
            klient_id = "klient_id";
        }
        if(zamowienie_wartoscMin == null){
            zamowienie_wartoscMin = "zamowienie_wartosc";
        }
        if(zamowienie_wartoscMax == null){
            zamowienie_wartoscMax = "zamowienie_wartosc";
        }
        if(dateMin == null){
            dateMin = "zamowienie_data";
        }
        if(dateMax == null){
            dateMax = "zamowienie_date";
        }
        return zamowienieRepository.getFilteredOrdersDone(pracownik_id,klient_id,zamowienie_wartoscMin,zamowienie_wartoscMax,dateMin,dateMax);
    }

}
