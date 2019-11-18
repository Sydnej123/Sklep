package pl.InternetowySklepMuzyczny.sklep.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.InternetowySklepMuzyczny.sklep.models.Zamowienie;

public interface ZamowienieRepository extends CrudRepository <Zamowienie,Integer>{
}
