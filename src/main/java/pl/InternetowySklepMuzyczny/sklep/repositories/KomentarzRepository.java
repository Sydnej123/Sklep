package pl.InternetowySklepMuzyczny.sklep.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.InternetowySklepMuzyczny.sklep.models.Komentarz;

import java.util.List;

import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface KomentarzRepository extends CrudRepository<Komentarz,Integer> {
    @Query("From Komentarz WHERE album_id = ?1")
    List<Komentarz> findByAlbumId(Integer id);
}
