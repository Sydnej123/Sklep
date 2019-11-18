package pl.InternetowySklepMuzyczny.sklep.models;


import lombok.Generated;

import javax.persistence.*;
import java.util.Set;

@Generated
@Entity
public class Gatunek_muzyki {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int gatunek_id;

    @OneToMany(mappedBy = "gatunek_muzyki")
    Set<Album> album;

    @Column(length = 25)
    String gatunek_nazwa;

}
