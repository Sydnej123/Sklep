package pl.InternetowySklepMuzyczny.sklep.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.InternetowySklepMuzyczny.sklep.models.Album;
@Repository
public interface AlbumRepository extends CrudRepository<Album,Integer> {
    @Query("SELECT count(album_id) FROM Album where gatunek_id =?1")
    long countGatunek(int gatunek_id);
}