package pl.InternetowySklepMuzyczny.sklep.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Pracownik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer pracownik_id;

    public Integer getPracownik_id() {
        return pracownik_id;
    }

    public void setPracownik_id(Integer pracownik_id) {
        this.pracownik_id = pracownik_id;
    }

    @Override
    public String toString() {
        return "Pracownik{" +
                "pracownik_id=" + pracownik_id +
                ", pracownik_imie='" + pracownik_imie + '\'' +
                ", pracownik_nazwisko='" + pracownik_nazwisko + '\'' +
                '}';
    }

    public List<Zamowienie> getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(List<Zamowienie> zamowienia) {
        this.zamowienia = zamowienia;
    }

    @OneToMany(mappedBy = "pracownik")
    List<Zamowienie> zamowienia = new ArrayList<>();
    @Column(length = 20)
    String pracownik_imie;
    @Column(length = 20)
    String pracownik_nazwisko;
    int pracownik_poziom_uprawnien;
    String pracownik_kod;

    public String getPracownik_kod() {
        return pracownik_kod;
    }

    public void setPracownik_kod(String pracownik_kod) {
        this.pracownik_kod = pracownik_kod;
    }


    public String getPracownik_imie() {
        return pracownik_imie;
    }

    public void setPracownik_imie(String pracownik_imie) {
        this.pracownik_imie = pracownik_imie;
    }

    public String getPracownik_nazwisko() {
        return pracownik_nazwisko;
    }

    public void setPracownik_nazwisko(String pracownik_nazwisko) {
        this.pracownik_nazwisko = pracownik_nazwisko;
    }

    public int getPracownik_poziom_uprawnien() {
        return pracownik_poziom_uprawnien;
    }

    public void setPracownik_poziom_uprawnien(int pracownik_poziom_uprawnien) {
        this.pracownik_poziom_uprawnien = pracownik_poziom_uprawnien;
    }
}
