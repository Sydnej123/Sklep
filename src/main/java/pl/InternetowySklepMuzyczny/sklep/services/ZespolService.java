package pl.InternetowySklepMuzyczny.sklep.services;

import pl.InternetowySklepMuzyczny.sklep.models.Zespol;

import java.util.ArrayList;

public interface ZespolService {
    ArrayList<Zespol> findAll();
}