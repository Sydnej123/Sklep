package pl.InternetowySklepMuzyczny.sklep.models;

import lombok.Generated;

import javax.persistence.*;
import java.util.Set;

@Generated
@Entity
public class Zespol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int zespol_id;

    @OneToMany(mappedBy = "zespol")
    Set<Album> album;
    @Column(length = 20)
    String zespol_nazwa;

}
