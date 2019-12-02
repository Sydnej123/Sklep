package pl.InternetowySklepMuzyczny.sklep.models;

import lombok.Generated;

import javax.persistence.*;

@Entity

public class Szczegoly_zamowienia {

    @EmbeddedId
    private CompositePrimaryKeySzcze_zam compositePrimaryKeySzcze_zam;
    @ManyToOne
    private Zamowienie zamowienie;
    @ManyToOne
    private Album album;
    @Column(nullable = false)
    int szcze_zam_ilosc;

    public CompositePrimaryKeySzcze_zam getCompositePrimaryKeySzcze_zam() {
        return compositePrimaryKeySzcze_zam;
    }

    public void setCompositePrimaryKeySzcze_zam(CompositePrimaryKeySzcze_zam compositePrimaryKeySzcze_zam) {
        this.compositePrimaryKeySzcze_zam = compositePrimaryKeySzcze_zam;
    }

    public Zamowienie getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public int getSzcze_zam_ilosc() {
        return szcze_zam_ilosc;
    }

    public void setSzcze_zam_ilosc(int szcze_zam_ilosc) {
        this.szcze_zam_ilosc = szcze_zam_ilosc;
    }
}
