package pl.InternetowySklepMuzyczny.sklep.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.InternetowySklepMuzyczny.sklep.models.Gatunek_muzyki;

public interface Gatunek_muzykiRepository extends CrudRepository<Gatunek_muzyki,Integer> {
}
