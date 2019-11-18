package pl.InternetowySklepMuzyczny.sklep.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.InternetowySklepMuzyczny.sklep.models.Komentarz;
@Repository
public interface KomentarzRepository extends CrudRepository<Komentarz,Integer> {
}
