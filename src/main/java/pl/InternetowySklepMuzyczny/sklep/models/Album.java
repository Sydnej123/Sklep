package pl.InternetowySklepMuzyczny.sklep.models;

import lombok.Generated;

import javax.persistence.*;

@Generated
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int album_id;

    private int gatunek_id;
    private int zespol_id;
    @Column(nullable=false,length = 30)
    private String album_nazwa;
    private int album_ilosc;
    private float album_cena;
    private String album_opis;
    private String album_zdjecie_sciezka;


}
