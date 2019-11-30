package pl.InternetowySklepMuzyczny.sklep.services;

import pl.InternetowySklepMuzyczny.sklep.models.Klient;

import java.util.ArrayList;

public interface KlientService {
    ArrayList<Integer> getClientByUsername(String username);
    ArrayList<String> getPasswordById(Integer id);
    ArrayList<Integer> findbyEmail(String email);
    void save(Klient klient);
    ArrayList<String> findLoginByID(Integer id);
}
