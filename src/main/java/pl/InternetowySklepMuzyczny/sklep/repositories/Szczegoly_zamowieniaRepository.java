package pl.InternetowySklepMuzyczny.sklep.repositories;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.InternetowySklepMuzyczny.sklep.models.CompositePrimaryKeySzcze_zam;
import pl.InternetowySklepMuzyczny.sklep.models.Szczegoly_zamowienia;

import java.util.Date;
import java.util.List;

@Repository
public interface Szczegoly_zamowieniaRepository  extends CrudRepository<Szczegoly_zamowienia, CompositePrimaryKeySzcze_zam> {
    @Query("From Szczegoly_zamowienia WHERE zamowienie_id=?1")
    List<Szczegoly_zamowienia> findByZamowienieId(Integer id);
    @Query("SELECT SUM(s.szcze_zam_ilosc) FROM Szczegoly_zamowienia s INNER JOIN s.zamowienie as z where z.zamowienie_status=1 and z.zamowienie_data >= ?1 and z.zamowienie_data<= ?2")
    Long getAlbumsCount(Date dateFrom, Date dateTo);
    @Query("SELECT a.album_nazwa  FROM Szczegoly_zamowienia s INNER JOIN s.album as a INNER JOIN s.zamowienie as z  WHERE z.zamowienie_data >=?1 and z.zamowienie_data <=?2 GROUP BY a.album_id ORDER BY SUM(s.szcze_zam_ilosc) DESC")
    List<String> getBestSellerAlbumName(Date dateFrom, Date dateTo, Pageable pageable);
}
