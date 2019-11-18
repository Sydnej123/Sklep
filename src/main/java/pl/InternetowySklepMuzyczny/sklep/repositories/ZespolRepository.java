package pl.InternetowySklepMuzyczny.sklep.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.InternetowySklepMuzyczny.sklep.models.Zespol;
@Repository
public interface ZespolRepository extends CrudRepository<Zespol,Integer> {
}
