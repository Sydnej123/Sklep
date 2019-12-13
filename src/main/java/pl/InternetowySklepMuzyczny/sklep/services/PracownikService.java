package pl.InternetowySklepMuzyczny.sklep.services;

import pl.InternetowySklepMuzyczny.sklep.models.Pracownik;

import java.util.Date;
import java.util.List;

public interface PracownikService {
    List<Pracownik> getPracownikByID(Integer id);
    void save(Pracownik pracownik);
    List<Pracownik> findAll();
    String getBestSeller(Date dateFrom, Date dateTo);
}
