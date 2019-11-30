package pl.InternetowySklepMuzyczny.sklep.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.InternetowySklepMuzyczny.sklep.models.Gatunek_muzyki;
@Repository
public interface Gatunek_muzykiRepository extends CrudRepository<Gatunek_muzyki,Integer> {

}
