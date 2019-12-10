package pl.InternetowySklepMuzyczny.sklep.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.InternetowySklepMuzyczny.sklep.models.Zamowienie;

import java.util.List;

import static org.springframework.http.HttpHeaders.FROM;

@Repository
public interface ZamowienieRepository extends CrudRepository <Zamowienie,Integer>{
    @Query("FROM Zamowienie where klient_id=?1")
    List<Zamowienie> getOrdersByClientId(Integer id);
    @Query("SELECT COUNT(zamowienie_id) FROM Zamowienie where klient_id=?1")
    Integer getOrderCountById(Integer id);
    @Query("FROM Zamowienie where zamowienie_status=?1")
    List<Zamowienie> getOrdersByStatus(Integer status);
    @Query("FROM Zamowienie WHERE zamowienie_status=1 AND pracownik_id=?1 AND klient_id=?2 AND zamowienie_wartosc>= ?3 AND zamowienie_wartosc<=?4 AND zamowienie_data >= ?5  AND zamowienie_data <= ?6")
    List<Zamowienie> getFilteredOrdersDone(String pracownik_id, String klient_id, String zamowienie_wartoscMin, String zamowienie_wartoscMax, String dateMin, String dateMax);

}
