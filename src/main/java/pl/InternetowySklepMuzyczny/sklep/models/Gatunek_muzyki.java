package pl.InternetowySklepMuzyczny.sklep.models;


import lombok.Generated;

import javax.persistence.*;

@Generated
@Entity
public class Gatunek_muzyki {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int gatunek_id;


    @Column(length = 25)
    String gatunek_nazwa;

}
