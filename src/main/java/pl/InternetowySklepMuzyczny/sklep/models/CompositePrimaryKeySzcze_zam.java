package pl.InternetowySklepMuzyczny.sklep.models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompositePrimaryKeySzcze_zam implements Serializable {
    private int zamowienie_id;
    private int album_id;

    public CompositePrimaryKeySzcze_zam() {
    }

    public CompositePrimaryKeySzcze_zam(int zamowienie_id, int album_id) {
        this.zamowienie_id = zamowienie_id;
        this.album_id = album_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompositePrimaryKeySzcze_zam)) return false;
        CompositePrimaryKeySzcze_zam that = (CompositePrimaryKeySzcze_zam) o;
        return zamowienie_id == that.zamowienie_id &&
                album_id == that.album_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(zamowienie_id, album_id);
    }
}
