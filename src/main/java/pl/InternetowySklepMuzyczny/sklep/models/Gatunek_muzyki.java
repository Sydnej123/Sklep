package pl.InternetowySklepMuzyczny.sklep.models;


import lombok.Generated;

import javax.persistence.*;
import java.util.Set;

@Generated
@Entity
public class Gatunek_muzyki {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int gatunek_id;

    @OneToMany(mappedBy = "gatunek_muzyki")
    Set<Album> album;

    @Column(length = 25)
    String gatunek_nazwa;

    public int getGatunek_id() {
        return gatunek_id;
    }

    public void setGatunek_id(int gatunek_id) {
        this.gatunek_id = gatunek_id;
    }

    public Set<Album> getAlbum() {
        return album;
    }

    public void setAlbum(Set<Album> album) {
        this.album = album;
    }

    public String getGatunek_nazwa() {
        return gatunek_nazwa;
    }

    public void setGatunek_nazwa(String gatunek_nazwa) {
        this.gatunek_nazwa = gatunek_nazwa;
    }

    @Override
    public String toString() {
        return gatunek_nazwa;
    }
}
