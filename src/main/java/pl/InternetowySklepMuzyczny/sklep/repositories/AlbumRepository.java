package pl.InternetowySklepMuzyczny.sklep.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.InternetowySklepMuzyczny.sklep.models.Album;

import java.util.List;

@Repository
public interface AlbumRepository extends CrudRepository<Album,Integer> {
    @Query("SELECT count(album_id) FROM Album where gatunek_id =?1")
    long countGatunek(int gatunek_id);
    @Query("FROM Album where gatunek_id=?1")
    List<Album> findByGenre(Integer id);
    @Query("SELECT COUNT(album_id) FROM Album where zespol_id=?1")
    Integer countBandAlbums(Integer bandID);

}