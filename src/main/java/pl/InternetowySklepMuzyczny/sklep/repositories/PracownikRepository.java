package pl.InternetowySklepMuzyczny.sklep.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.InternetowySklepMuzyczny.sklep.models.Pracownik;

public interface PracownikRepository extends CrudRepository<Pracownik,Integer> {
}
