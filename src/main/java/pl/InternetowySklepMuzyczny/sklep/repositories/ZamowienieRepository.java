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
}
