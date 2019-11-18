package pl.InternetowySklepMuzyczny.sklep.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.InternetowySklepMuzyczny.sklep.models.CompositePrimaryKeySzcze_zam;
import pl.InternetowySklepMuzyczny.sklep.models.Szczegoly_zamowienia;
@Repository
public interface Szczegoly_zamowieniaRepository  extends CrudRepository<Szczegoly_zamowienia, CompositePrimaryKeySzcze_zam> {
}
