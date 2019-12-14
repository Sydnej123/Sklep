package pl.InternetowySklepMuzyczny.sklep.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.InternetowySklepMuzyczny.sklep.models.Album;
import pl.InternetowySklepMuzyczny.sklep.models.Pracownik;
import pl.InternetowySklepMuzyczny.sklep.repositories.AlbumRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AlbumServiceImp implements AlbumService{
    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public long countGatunek(int gatunek_id) {
        return albumRepository.countGatunek(gatunek_id);
    }

    @Override
    public List<Album> findAll() {
        List<Album> result = new ArrayList<>();
        for(Album album: albumRepository.findAll()){
            result.add(album);
        }
        return result;
    }

    @Override
    public List<Album> findByGenre(Integer id) {
        return albumRepository.findByGenre(id);
    }

    @Override
    public Integer countBandAlbums(Integer bandID) {
        return albumRepository.countBandAlbums(bandID);
    }

    @Override
    public Album findById(Integer id) {
        return albumRepository.findById(id).get();
    }

    @Override
    public void save(Album album) {
        albumRepository.save(album);
    }

    @Override
    public List<Album> missingAlbums() {
        return albumRepository.missingAlbums();
    }

    @Override
    public void delete(Album a) {
        albumRepository.delete(a);
    }


}
