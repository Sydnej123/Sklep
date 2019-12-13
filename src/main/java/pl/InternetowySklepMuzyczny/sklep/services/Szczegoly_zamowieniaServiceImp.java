package pl.InternetowySklepMuzyczny.sklep.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.InternetowySklepMuzyczny.sklep.models.CompositePrimaryKeySzcze_zam;
import pl.InternetowySklepMuzyczny.sklep.models.Szczegoly_zamowienia;
import pl.InternetowySklepMuzyczny.sklep.repositories.Szczegoly_zamowieniaRepository;

import java.util.Date;
import java.util.List;

@Service
public class Szczegoly_zamowieniaServiceImp implements Szczegoly_zamowieniaService {
    @Autowired
    Szczegoly_zamowieniaRepository szczegoly_zamowieniaRepository;
    @Override
    public Szczegoly_zamowienia findById(CompositePrimaryKeySzcze_zam compositePrimaryKeySzcze_zam) {
        return szczegoly_zamowieniaRepository.findById(compositePrimaryKeySzcze_zam).get();
    }

    @Override
    public List<Szczegoly_zamowienia> findByZamowienieId(Integer id) {
        return szczegoly_zamowieniaRepository.findByZamowienieId(id);
    }

    @Override
    public Long getAlbumsCount(Date dateFrom, Date dateTo) {
        return szczegoly_zamowieniaRepository.getAlbumsCount(dateFrom,dateTo);
    }

    @Override
    public String getBestSellerAlbumName(Date dateFrom, Date dateTo) {
        Pageable pageable = PageRequest.of(0,1);
        return (!szczegoly_zamowieniaRepository.getBestSellerAlbumName(dateFrom, dateTo,pageable).get(0).isEmpty())?szczegoly_zamowieniaRepository.getBestSellerAlbumName(dateFrom, dateTo,pageable).get(0):"Błąd";
    }
}
