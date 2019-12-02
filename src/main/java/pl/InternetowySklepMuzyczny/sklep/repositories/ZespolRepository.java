package pl.InternetowySklepMuzyczny.sklep.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.InternetowySklepMuzyczny.sklep.models.Zespol;

import static org.springframework.http.HttpHeaders.FROM;

@Repository
public interface ZespolRepository extends CrudRepository<Zespol,Integer> {

}
