package pl.InternetowySklepMuzyczny.sklep.models;

import lombok.Generated;

import javax.persistence.*;
import java.sql.Time;

@Generated
@Entity

public class Utwor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int utwor_id;
    int album_id;
    @Column(length = 40)
    String utwor_nazwa;
    Time utwor_czas_trwania;
}
