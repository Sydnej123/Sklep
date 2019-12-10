package pl.InternetowySklepMuzyczny.sklep.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Komentarz {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int komentarz_id;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    @ManyToOne
    @JoinColumn(name = "klient_id", nullable = false)
    private Klient klient;

    @Column(length=1000,nullable = false)
    String komentarz_tresc;
    int komentarz_ocena;
    Date komentarz_data;

    public int getKomentarz_id() {
        return komentarz_id;
    }

    public void setKomentarz_id(int komentarz_id) {
        this.komentarz_id = komentarz_id;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public String getKomentarz_tresc() {
        return komentarz_tresc;
    }

    public void setKomentarz_tresc(String komentarz_tresc) {
        this.komentarz_tresc = komentarz_tresc;
    }

    public int getKomentarz_ocena() {
        return komentarz_ocena;
    }

    public void setKomentarz_ocena(int komentarz_ocena) {
        this.komentarz_ocena = komentarz_ocena;
    }

    public Date getKomentarz_data() {
        return komentarz_data;
    }

    public void setKomentarz_data(Date komentarz_data) {
        this.komentarz_data = komentarz_data;
    }
}
