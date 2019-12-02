package pl.InternetowySklepMuzyczny.sklep.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.InternetowySklepMuzyczny.sklep.models.Pracownik;

import java.util.List;

@Repository
public interface PracownikRepository extends CrudRepository<Pracownik,Integer> {
    @Query("FROM Pracownik where pracownik_id=?1")
    List<Pracownik> getPracownikByID(Integer id);
}
