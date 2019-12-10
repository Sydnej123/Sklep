package pl.InternetowySklepMuzyczny.sklep.models;

import javax.persistence.*;
import java.sql.Time;

@Entity

public class Utwor {
    public int getUtwor_id() {
        return utwor_id;
    }

    public void setUtwor_id(int utwor_id) {
        this.utwor_id = utwor_id;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public String getUtwor_nazwa() {
        return utwor_nazwa;
    }

    public void setUtwor_nazwa(String utwor_nazwa) {
        this.utwor_nazwa = utwor_nazwa;
    }

    public Time getUtwor_czas_trwania() {
        return utwor_czas_trwania;
    }

    public void setUtwor_czas_trwania(Time utwor_czas_trwania) {
        this.utwor_czas_trwania = utwor_czas_trwania;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int utwor_id;
    int album_id;
    @Column(length = 40)
    String utwor_nazwa;
    Time utwor_czas_trwania;
}
