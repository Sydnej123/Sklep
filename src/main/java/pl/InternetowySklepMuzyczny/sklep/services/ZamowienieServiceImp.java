package pl.InternetowySklepMuzyczny.sklep.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.InternetowySklepMuzyczny.sklep.models.Zamowienie;
import pl.InternetowySklepMuzyczny.sklep.repositories.ZamowienieRepository;

import java.util.Date;
import java.util.List;
@Service
public class ZamowienieServiceImp implements ZamowienieService {
    @Autowired
    ZamowienieRepository zamowienieRepository;
    @Autowired
    Szczegoly_zamowieniaServiceImp szczegoly_zamowieniaServiceImp;
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
    public Long getOrdersCount(Date dateFrom, Date dateTo) {
        return zamowienieRepository.getOrdersCount(dateFrom,dateTo);
    }

    @Override
    public Double getSumValueOrders(Date dateFrom, Date dateTo) {
        return zamowienieRepository.getSumValueOrders(dateFrom, dateTo);
    }

    @Override
    public Double sumValue(Date dateFrom, Date dateTo) {
        return zamowienieRepository.sumValue(dateFrom, dateTo);
    }

    @Override
    public Double avarageAlbumsPerOrder(Date dateFrom, Date dateTo) {
        return szczegoly_zamowieniaServiceImp.getAlbumsCount(dateFrom, dateTo)/(double)(getOrdersCount(dateFrom,dateTo)==0?1:getOrdersCount(dateFrom, dateTo));
    }

    @Override
    public Double avarageOrderValue(Date dateFrom, Date dateTo) {
        return sumValue(dateFrom,dateTo)/((getOrdersCount(dateFrom,dateTo)==0?1:getOrdersCount(dateFrom, dateTo)));
    }

    @Override
    public Long canceledOrders(Date dateFrom, Date dateTo) {
        return zamowienieRepository.canceledOrders(dateFrom, dateTo);
    }


}
