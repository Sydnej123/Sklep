package pl.InternetowySklepMuzyczny.sklep.models;

import lombok.Generated;

import javax.persistence.*;

@Generated
@Entity
public class Pracownik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int pracownik_id;

    @Column(length = 20)
    String pracownik_imie;
    @Column(length = 20)
    String pracownik_nazwisko;
    int pracownik_poziom_uprawnien;
}
