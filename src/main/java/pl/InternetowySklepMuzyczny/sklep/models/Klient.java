package pl.InternetowySklepMuzyczny.sklep.models;

import lombok.Generated;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Generated
public class Klient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int klient_id;
    @Column(length = 20)
    private String klient_imie;
    @Column(length = 20)
    private String klient_nazwisko;
    @Column(length = 20, nullable=false)
    private String klient_login;
    @Column(length = 60, nullable=false)
    private String klient_haslo;
    @Column(length = 30)
    private String klient_email;
    @Column(length = 20)
    private String klient_miasto;
    @Column(length = 20)
    private String klient_ulica;
    @Column(nullable = false)
    private int klient_nr_domu;
    @Column(nullable = false)
    private int klient_nr_mieszkania;
    private String klient_kod_pocztowy;
}
