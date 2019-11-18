package pl.InternetowySklepMuzyczny.sklep.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.InternetowySklepMuzyczny.sklep.models.Album;

public interface AlbumRepository extends CrudRepository<Album,Integer> {
}
