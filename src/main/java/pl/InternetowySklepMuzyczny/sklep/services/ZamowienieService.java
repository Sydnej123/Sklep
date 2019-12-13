package pl.InternetowySklepMuzyczny.sklep.services;

import org.springframework.stereotype.Service;
import pl.InternetowySklepMuzyczny.sklep.models.Zamowienie;

import java.util.Date;
import java.util.List;

public interface ZamowienieService {
    List<Zamowienie> getOrderByClientId(Integer id);
    Integer getOrderCountById(Integer id);
    List<Zamowienie> getOrderByStatus(Integer status);
    void save(Zamowienie zamowienie);
     Long getOrdersCount(Date dateFrom, Date dateTo);
    Double getSumValueOrders(Date dateFrom, Date dateTo);


}
