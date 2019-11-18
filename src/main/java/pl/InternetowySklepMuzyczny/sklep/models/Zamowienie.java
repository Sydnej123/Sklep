package pl.InternetowySklepMuzyczny.sklep.models;

import lombok.Generated;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Generated
@Entity

public class Zamowienie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int zamowienie_id;
    int pracownik_id;
    int klient_id;
    float zamowienie_wartosc;
    @Column(length = 1000)
    String zamowienie_komentarz;
    Date zamowienie_data;
    @OneToMany(mappedBy = "zamowienie")
    Set<Szczegoly_zamowienia> szczegoly_zamowienia;
}
