package pl.InternetowySklepMuzyczny.sklep.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
public class Pracownik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int pracownik_id;
    @OneToMany(mappedBy = "pracownik")
    List<Zamowienie> zamowienia = new ArrayList<>();
    @Column(length = 20)
    String pracownik_imie;
    @Column(length = 20)
    String pracownik_nazwisko;
    int pracownik_poziom_uprawnien;

    public String getPracownik_kod() {
        return pracownik_kod;
    }

    public void setPracownik_kod(String pracownik_kod) {
        this.pracownik_kod = pracownik_kod;
    }

    String pracownik_kod;

    public int getPracownik_id() {
        return pracownik_id;
    }

    public void setPracownik_id(int pracownik_id) {
        this.pracownik_id = pracownik_id;
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
