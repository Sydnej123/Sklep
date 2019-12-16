package pl.InternetowySklepMuzyczny.sklep.models;

import lombok.Generated;

import javax.persistence.*;

@Entity

public class Szczegoly_zamowienia {

    @EmbeddedId
    private CompositePrimaryKeySzcze_zam compositePrimaryKeySzcze_zam;

    public Szczegoly_zamowienia() {
    }

    @ManyToOne
    @JoinColumn(name = "album_id", insertable = false, updatable = false)
    private Album album;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }


    public Integer getSzcze_zam_ilosc() {
        return szcze_zam_ilosc;
    }

    public Szczegoly_zamowienia(CompositePrimaryKeySzcze_zam compositePrimaryKeySzcze_zam) {
        this.compositePrimaryKeySzcze_zam = compositePrimaryKeySzcze_zam;
    }

    public void setSzcze_zam_ilosc(Integer szcze_zam_ilosc) {
        this.szcze_zam_ilosc = szcze_zam_ilosc;
    }

    public Zamowienie getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
    }

    Integer szcze_zam_ilosc;

    public Double getSzcze_zam_cena_jednostki() {
        return szcze_zam_cena_jednostki;
    }

    public void setSzcze_zam_cena_jednostki(Double szcze_zam_cena_jednostki) {
        this.szcze_zam_cena_jednostki = szcze_zam_cena_jednostki;
    }

    Double szcze_zam_cena_jednostki;
    @ManyToOne
    @JoinColumn(name = "zamowienie_id", insertable = false, updatable = false)
    private Zamowienie zamowienie;

    public CompositePrimaryKeySzcze_zam getCompositePrimaryKeySzcze_zam() {
        return compositePrimaryKeySzcze_zam;
    }

    public void setCompositePrimaryKeySzcze_zam(CompositePrimaryKeySzcze_zam compositePrimaryKeySzcze_zam) {
        this.compositePrimaryKeySzcze_zam = compositePrimaryKeySzcze_zam;
    }
}