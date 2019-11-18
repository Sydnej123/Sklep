package pl.InternetowySklepMuzyczny.sklep.models;

import lombok.Generated;

import javax.persistence.*;
import java.util.Set;

@Generated
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int album_id;
    @ManyToOne
    private Gatunek_muzyki gatunek_muzyki;
    @ManyToOne
    private Zespol zespol;

    @OneToMany(mappedBy="album")
    private Set<Komentarz> komentarz;

    @OneToMany(mappedBy = "album")
    Set<Szczegoly_zamowienia> szczegoly_zamowienia;


    @Column(nullable=false,length = 30)
    private String album_nazwa;
    private int album_ilosc;
    private float album_cena;
    private String album_opis;
    private String album_zdjecie_sciezka;


}
