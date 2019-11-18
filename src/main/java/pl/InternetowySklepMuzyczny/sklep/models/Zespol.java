package pl.InternetowySklepMuzyczny.sklep.models;

import lombok.Generated;

import javax.persistence.*;

@Generated
@Entity
public class Zespol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int zespol_id;
    @Column(length = 20)
    String zespol_nazwa;

}
