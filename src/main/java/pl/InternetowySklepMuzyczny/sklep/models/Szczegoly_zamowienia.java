package pl.InternetowySklepMuzyczny.sklep.models;

import lombok.Generated;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Generated
@Entity

public class Szczegoly_zamowienia {

    @EmbeddedId
    private CompositePrimaryKeySzcze_zam compositePrimaryKeySzcze_zam;
    @ManyToOne
    private Zamowienie zamowienie;
    @ManyToOne
    private Album album;
    @Column(nullable = false)
    int szcze_zam_ilosc;
}
