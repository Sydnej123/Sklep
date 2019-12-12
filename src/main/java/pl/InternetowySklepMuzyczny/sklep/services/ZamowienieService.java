package pl.InternetowySklepMuzyczny.sklep.services;

import pl.InternetowySklepMuzyczny.sklep.models.Zamowienie;

import java.util.List;

public interface ZamowienieService {
    List<Zamowienie> getOrderByClientId(Integer id);
    Integer getOrderCountById(Integer id);
    List<Zamowienie> getOrderByStatus(Integer status);
    void save(Zamowienie zamowienie);
}
