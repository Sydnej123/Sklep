package pl.InternetowySklepMuzyczny.sklep.services;

import java.util.ArrayList;

public interface KlientService {
    ArrayList<Integer> getClientByUsername(String username);
}
