package pl.InternetowySklepMuzyczny.sklep.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Entity
public class Album {
    public List<Komentarz> getKomentarz() {
        return komentarz;
    }

    public void setKomentarz(List<Komentarz> komentarz) {
        this.komentarz = komentarz;
    }

    public List<Szczegoly_zamowienia> getSzczegoly_zamowienia() {
        return szczegoly_zamowienia;
    }

    public void setSzczegoly_zamowienia(List<Szczegoly_zamowienia> szczegoly_zamowienia) {
        this.szczegoly_zamowienia = szczegoly_zamowienia;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int album_id;
    @ManyToOne
    @JoinColumn(name = "gatunek_id", nullable = false)
    private Gatunek_muzyki gatunek_muzyki;
    @ManyToOne
    @JoinColumn(name = "zespol_id", nullable = false)
    private Zespol zespol;

    @OneToMany(mappedBy="album")
    private List<Komentarz> komentarz = new ArrayList<>();

    @OneToMany(mappedBy = "album")
    List<Szczegoly_zamowienia> szczegoly_zamowienia= new ArrayList<>();


    @Column(nullable=false,length = 30)
    private String album_nazwa;
    private int album_ilosc;
    private float album_cena;
    private String album_opis;
    private String album_zdjecie_sciezka;


    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public Gatunek_muzyki getGatunek_muzyki() {
        return gatunek_muzyki;
    }

    public void setGatunek_muzyki(Gatunek_muzyki gatunek_muzyki) {
        this.gatunek_muzyki = gatunek_muzyki;
    }

    public Zespol getZespol() {
        return zespol;
    }

    public void setZespol(Zespol zespol) {
        this.zespol = zespol;
    }



    public String getAlbum_nazwa() {
        return album_nazwa;
    }

    public void setAlbum_nazwa(String album_nazwa) {
        this.album_nazwa = album_nazwa;
    }

    public int getAlbum_ilosc() {
        return album_ilosc;
    }

    public void setAlbum_ilosc(int album_ilosc) {
        this.album_ilosc = album_ilosc;
    }

    public float getAlbum_cena() {
        return album_cena;
    }

    public void setAlbum_cena(float album_cena) {
        this.album_cena = album_cena;
    }

    public String getAlbum_opis() {
        return album_opis;
    }

    public void setAlbum_opis(String album_opis) {
        this.album_opis = album_opis;
    }

    public String getAlbum_zdjecie_sciezka() {
        return album_zdjecie_sciezka;
    }

    public void setAlbum_zdjecie_sciezka(String album_zdjecie_sciezka) {
        this.album_zdjecie_sciezka = album_zdjecie_sciezka;
    }
}
