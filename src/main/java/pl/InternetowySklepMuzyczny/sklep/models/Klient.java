package pl.InternetowySklepMuzyczny.sklep.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Klient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int klient_id;
    @Column(length = 20)
    private String klient_imie;
    @Column(length = 20)
    private String klient_nazwisko;
    @Column(length = 20, nullable=false)
    private String klient_login;
    @Column(length = 60, nullable=false)
    private String klient_haslo;
    @Column(length = 30)
    private String klient_email;
    @Column(length = 20)
    private String klient_miasto;
    @Column(length = 20)
    private String klient_ulica;
    @Column(nullable = false)
    private Integer klient_nr_domu;
    @Column(nullable = false)
    private Integer klient_nr_mieszkania;
    private String klient_kod_pocztowy;

    @OneToMany(mappedBy = "klient")
    private Set<Komentarz> komentarz;

    public int getKlient_id() {
        return klient_id;
    }

    public void setKlient_id(int klient_id) {
        this.klient_id = klient_id;
    }

    public String getKlient_imie() {
        return klient_imie;
    }

    public void setKlient_imie(String klient_imie) {
        this.klient_imie = klient_imie;
    }

    public String getKlient_nazwisko() {
        return klient_nazwisko;
    }

    public void setKlient_nazwisko(String klient_nazwisko) {
        this.klient_nazwisko = klient_nazwisko;
    }

    public String getKlient_login() {
        return klient_login;
    }

    public void setKlient_login(String klient_login) {
        this.klient_login = klient_login;
    }

    public String getKlient_haslo() {
        return klient_haslo;
    }

    public void setKlient_haslo(String klient_haslo) {
        this.klient_haslo = klient_haslo;
    }

    public String getKlient_email() {
        return klient_email;
    }

    public void setKlient_email(String klient_email) {
        this.klient_email = klient_email;
    }

    public String getKlient_miasto() {
        return klient_miasto;
    }

    public void setKlient_miasto(String klient_miasto) {
        this.klient_miasto = klient_miasto;
    }

    public String getKlient_ulica() {
        return klient_ulica;
    }

    public void setKlient_ulica(String klient_ulica) {
        this.klient_ulica = klient_ulica;
    }



    public String getKlient_kod_pocztowy() {
        return klient_kod_pocztowy;
    }

    public void setKlient_kod_pocztowy(String klient_kod_pocztowy) {
        this.klient_kod_pocztowy = klient_kod_pocztowy;
    }

    public Set<Komentarz> getKomentarz() {
        return komentarz;
    }

    public void setKomentarz(Set<Komentarz> komentarz) {
        this.komentarz = komentarz;
    }

    public Integer getKlient_nr_domu() {
        return klient_nr_domu;
    }

    public void setKlient_nr_domu(Integer klient_nr_domu) {
        this.klient_nr_domu = klient_nr_domu;
    }

    public Integer getKlient_nr_mieszkania() {
        return klient_nr_mieszkania;
    }

    public void setKlient_nr_mieszkania(Integer klient_nr_mieszkania) {
        this.klient_nr_mieszkania = klient_nr_mieszkania;
    }
}
