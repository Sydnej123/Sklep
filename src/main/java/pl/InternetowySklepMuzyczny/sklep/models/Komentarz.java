package pl.InternetowySklepMuzyczny.sklep.models;

import lombok.Generated;

import javax.persistence.*;
import java.util.Date;

@Generated
@Entity
public class Komentarz {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int komentarz_id;

    int album_id;
    int klient_id;
    @Column(length=1000,nullable = false)
    String komentarz_tresc;
    int komentarz_ocena;
    Date komentarz_data;
}
