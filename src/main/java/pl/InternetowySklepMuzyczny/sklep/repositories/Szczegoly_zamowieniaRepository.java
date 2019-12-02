package pl.InternetowySklepMuzyczny.sklep.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.InternetowySklepMuzyczny.sklep.models.CompositePrimaryKeySzcze_zam;
import pl.InternetowySklepMuzyczny.sklep.models.Szczegoly_zamowienia;

import java.util.List;

@Repository
public interface Szczegoly_zamowieniaRepository  extends CrudRepository<Szczegoly_zamowienia, CompositePrimaryKeySzcze_zam> {
    @Query("From Szczegoly_zamowienia WHERE zamowienie_id=?1")
    List<Szczegoly_zamowienia> findByZamowienieId(Integer id);
}
