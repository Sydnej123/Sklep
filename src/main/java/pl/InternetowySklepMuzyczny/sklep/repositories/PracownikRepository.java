package pl.InternetowySklepMuzyczny.sklep.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.InternetowySklepMuzyczny.sklep.models.Pracownik;

import java.util.Date;
import java.util.List;

@Repository
public interface PracownikRepository extends CrudRepository<Pracownik,Integer> {
    @Query("FROM Pracownik where pracownik_id=?1")
    List<Pracownik> getPracownikByID(Integer id);
    @Query("SELECT p.pracownik_imie FROM Zamowienie as z INNER JOIN z.pracownik as p WHERE z.zamowienie_data >=?1 and z.zamowienie_data <=?2 GROUP BY(p.pracownik_id) ORDER BY COUNT(z.zamowienie_id) DESC")
    List<String> getBestSeller(Date dateFrom, Date dateTo);
}
