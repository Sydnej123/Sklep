package pl.InternetowySklepMuzyczny.sklep.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.InternetowySklepMuzyczny.sklep.models.Pracownik;
@Repository
public interface PracownikRepository extends CrudRepository<Pracownik,Integer> {
}
