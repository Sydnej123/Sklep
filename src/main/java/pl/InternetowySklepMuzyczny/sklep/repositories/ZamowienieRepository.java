package pl.InternetowySklepMuzyczny.sklep.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.InternetowySklepMuzyczny.sklep.models.Zamowienie;
@Repository
public interface ZamowienieRepository extends CrudRepository <Zamowienie,Integer>{
}
