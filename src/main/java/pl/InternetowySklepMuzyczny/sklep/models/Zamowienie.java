package pl.InternetowySklepMuzyczny.sklep.models;

import lombok.Generated;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity

public class Zamowienie {
    public Integer getZamowienie_id() {
        return zamowienie_id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer zamowienie_id;
    @ManyToOne
    @JoinColumn(name = "pracownik_id", nullable = false)
    Pracownik pracownik;
    @ManyToOne
    @JoinColumn(name ="klient_id", nullable = false)
    Klient klient;
    Float zamowienie_wartosc;
    @Column(length = 1000)
    String zamowienie_komentarz;

    public void setZamowienie_id(Integer zamowienie_id) {
        this.zamowienie_id = zamowienie_id;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    Date zamowienie_data;
    @OneToMany(mappedBy = "zamowienie")
    Set<Szczegoly_zamowienia> szczegoly_zamowienia;
    public Integer getZamowienie_status() {
        return zamowienie_status;
    }

    public void setZamowienie_status(Integer zamowienie_status) {
        this.zamowienie_status = zamowienie_status;
    }

    Integer zamowienie_status;




    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }


    public Float getZamowienie_wartosc() {
        return zamowienie_wartosc;
    }

    public void setZamowienie_wartosc(Float zamowienie_wartosc) {
        this.zamowienie_wartosc = zamowienie_wartosc;
    }

    public String getZamowienie_komentarz() {
        return zamowienie_komentarz;
    }

    public void setZamowienie_komentarz(String zamowienie_komentarz) {
        this.zamowienie_komentarz = zamowienie_komentarz;
    }

    public Date getZamowienie_data() {
        return zamowienie_data;
    }

    public void setZamowienie_data(Date zamowienie_data) {
        this.zamowienie_data = zamowienie_data;
    }

    public Set<Szczegoly_zamowienia> getSzczegoly_zamowienia() {
        return szczegoly_zamowienia;
    }

    public void setSzczegoly_zamowienia(Set<Szczegoly_zamowienia> szczegoly_zamowienia) {
        this.szczegoly_zamowienia = szczegoly_zamowienia;
    }
}
