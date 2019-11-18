package pl.InternetowySklepMuzyczny.sklep.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.InternetowySklepMuzyczny.sklep.models.Utwor;
@Repository
public interface UtworRepository extends CrudRepository<Utwor,Integer>{
}
