package pl.InternetowySklepMuzyczny.sklep.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.InternetowySklepMuzyczny.sklep.models.CompositePrimaryKeySzcze_zam;
import pl.InternetowySklepMuzyczny.sklep.models.Szczegoly_zamowienia;

import java.util.Date;
import java.util.List;

public interface Szczegoly_zamowieniaService {
    Szczegoly_zamowienia findById(CompositePrimaryKeySzcze_zam compositePrimaryKeySzcze_zam);
    List<Szczegoly_zamowienia> findByZamowienieId(Integer id);
    Long getAlbumsCount(Date dateFrom, Date dateTo);
    String getBestSellerAlbumName(Date dateFrom, Date dateTo);
    void save(Szczegoly_zamowienia szczegoly_zamowienia);
}
