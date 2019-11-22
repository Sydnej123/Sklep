package pl.InternetowySklepMuzyczny.sklep.fxml_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.InternetowySklepMuzyczny.sklep.services.Gatunek_muzykiServiceImp;
import pl.InternetowySklepMuzyczny.sklep.services.KlientServiceImp;

@Controller
public class MainScreenController {

    @Autowired
    private Gatunek_muzykiServiceImp gatunek_muzykiServiceImp;

    @Autowired
    private KlientServiceImp klientService;

    public void buildScreen(){
        System.out.println(klientService.getPasswordById(0));
    }

}
