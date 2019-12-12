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


}
