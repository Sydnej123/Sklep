package pl.InternetowySklepMuzyczny.sklep.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.InternetowySklepMuzyczny.sklep.models.Klient;

import java.util.ArrayList;

@Repository
public interface KlientRepository extends CrudRepository<Klient,Integer> {
@Query("SELECT klient_id from Klient where klient_login=?1")
ArrayList<Integer> findByLogin(String login);
@Query("SELECT klient_haslo from Klient where klient_id=?1")
ArrayList<String> getPasswordById(int id);

}
