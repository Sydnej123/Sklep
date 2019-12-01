package pl.InternetowySklepMuzyczny.sklep.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
public class Zespol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int zespol_id;

    public List<Album> getAlbum() {
        return album;
    }

    public void setAlbum(List<Album> album) {
        this.album = album;
    }

    @OneToMany(mappedBy = "zespol")
    List<Album> album = new ArrayList<>();
    @Column(length = 20)
    String zespol_nazwa;

    public int getZespol_id() {
        return zespol_id;
    }

    public void setZespol_id(int zespol_id) {
        this.zespol_id = zespol_id;
    }



    public String getZespol_nazwa() {
        return zespol_nazwa;
    }

    public void setZespol_nazwa(String zespol_nazwa) {
        this.zespol_nazwa = zespol_nazwa;
    }


}
