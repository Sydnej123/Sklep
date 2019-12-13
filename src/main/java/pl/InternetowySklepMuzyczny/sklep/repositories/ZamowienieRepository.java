package pl.InternetowySklepMuzyczny.sklep.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.InternetowySklepMuzyczny.sklep.models.Album;
import pl.InternetowySklepMuzyczny.sklep.models.Zamowienie;

import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpHeaders.FROM;

@Repository
public interface ZamowienieRepository extends CrudRepository <Zamowienie,Integer> {
    @Query("FROM Zamowienie where klient_id=?1")
    List<Zamowienie> getOrdersByClientId(Integer id);

    @Query("SELECT COUNT(zamowienie_id) FROM Zamowienie where klient_id=?1")
    Integer getOrderCountById(Integer id);

    @Query("FROM Zamowienie where zamowienie_status=?1")
    List<Zamowienie> getOrdersByStatus(Integer status);

    @Query("SELECT COUNT(zamowienie_id) FROM Zamowienie where zamowienie_data >=?1 and zamowienie_data <=?2")
    Long getOrdersCount(Date dateFrom, Date dateTo);

    @Query("SELECT SUM(zamowienie_wartosc) FROM Zamowienie where zamowienie_data >=?1 and zamowienie_data <=?2")
    Double getSumValueOrders(Date dateFrom, Date dateTo);
    @Query("SELECT SUM(zamowienie_wartosc) from Zamowienie where zamowienie_status = 1 and  zamowienie_data >=?1 and zamowienie_data <=?2")
    Double sumValue(Date dateFrom, Date dateTo);
    @Query("SELECT COUNT(z.zamowienie_id) from Zamowienie as z where z.zamowienie_status = 2 and   z.zamowienie_data >=?1 and z.zamowienie_data <=?2")
    Long canceledOrders(Date dateFrom, Date dateTo);
}