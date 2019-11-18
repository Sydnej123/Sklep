package pl.InternetowySklepMuzyczny.sklep.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.InternetowySklepMuzyczny.sklep.models.Komentarz;

public interface KomentarzRepository extends CrudRepository<Komentarz,Integer> {
}
