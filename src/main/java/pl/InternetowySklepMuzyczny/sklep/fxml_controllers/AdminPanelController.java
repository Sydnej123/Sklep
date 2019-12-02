package pl.InternetowySklepMuzyczny.sklep.fxml_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import pl.InternetowySklepMuzyczny.sklep.models.Klient;
import pl.InternetowySklepMuzyczny.sklep.services.KlientServiceImp;
import pl.InternetowySklepMuzyczny.sklep.services.PracownikServiceImp;
import pl.InternetowySklepMuzyczny.sklep.services.ZespolServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
public class AdminPanelController {

    @FXML
    TitledPane editClientsTab;

    @FXML
    TableView editClientTable;

    @FXML
    TextField loginAddField, passwordAddField, nameAddField, secondnameAddField, emailAddField, cityAddField, streetAddField, houseNumberAddField, flatNumberAddField, zipCodeAddField;

    @FXML
    TextField filterLogin, filterName, filterSecondName, filterCity, filterZipCode;
    @FXML
    Label errorLabel;
    @FXML
    Button filterButton;
    @Autowired
    ZespolServiceImp zespolServiceImp;

    @Autowired
    PracownikServiceImp pracownikServiceImp;

    @Autowired
    KlientServiceImp klientServiceImp;

    public void insertNewClient(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Pattern numeric = Pattern.compile("([0-9])+");
        Klient klient = new Klient();
    if(!loginAddField.getText().isEmpty() && !passwordAddField.getText().isEmpty() && !emailAddField.getText().isEmpty() && numeric.matcher(houseNumberAddField.getText()).matches() && numeric.matcher(flatNumberAddField.getText()).matches()){
       klient.setKlient_login(loginAddField.getText());
       klient.setKlient_haslo(encoder.encode(passwordAddField.getText()));
       klient.setKlient_email(emailAddField.getText());
       klient.setKlient_imie(nameAddField.getText());
       klient.setKlient_nazwisko(secondnameAddField.getText());
       klient.setKlient_miasto(cityAddField.getText());
       klient.setKlient_ulica(streetAddField.getText());
       klient.setKlient_nr_domu(Integer.parseInt(houseNumberAddField.getText()));
       klient.setKlient_nr_mieszkania(Integer.parseInt(flatNumberAddField.getText()));
       klient.setKlient_kod_pocztowy(zipCodeAddField.getText());
       loginAddField.clear();
       passwordAddField.clear();
       emailAddField.clear();
       nameAddField.clear();
       secondnameAddField.clear();
       cityAddField.clear();
       streetAddField.clear();
       houseNumberAddField.clear();
       flatNumberAddField.clear();
       zipCodeAddField.clear();
       editClientTable.getItems().add(klient);
       klientServiceImp.save(klient);
       errorLabel.setVisible(false);
    }else{
        errorLabel.setText("Błąd wprowadzania danych");
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(true);
    }
    }
    public void insertDataIntoClientTable(){

    if(editClientTable.getColumns().isEmpty()) {
        TableColumn<Klient, String> idColumn = new TableColumn<>("Id");
        TableColumn<Klient, String> loginColumn = new TableColumn<>("Login");
        TableColumn<Klient, String> hasloColumn = new TableColumn<>("Hasło");
        TableColumn<Klient, String> imieColumn = new TableColumn<>("Imie");
        TableColumn<Klient, String> nazwiskoColumn = new TableColumn<>("Nazwisko");
        TableColumn<Klient, String> emailColumn = new TableColumn<>("E-mail");
        TableColumn<Klient, String> miastoColumn = new TableColumn<>("Miasto");
        TableColumn<Klient, String> ulicaColumn = new TableColumn<>("Ulica");
        TableColumn<Klient, String> nrdomuColumn = new TableColumn<>("Numer domu");
        TableColumn<Klient, String> nrmieszkaniaColumn = new TableColumn<>("Numer mieszkania");
        TableColumn<Klient, String> kodpocztowyColumn = new TableColumn<>("Kod pocztowy");

        idColumn.setCellValueFactory(new PropertyValueFactory("klient_id"));
        loginColumn.setCellValueFactory(new PropertyValueFactory("klient_login"));
        hasloColumn.setCellValueFactory(new PropertyValueFactory("klient_haslo"));
        imieColumn.setCellValueFactory(new PropertyValueFactory("klient_imie"));
        nazwiskoColumn.setCellValueFactory(new PropertyValueFactory("klient_nazwisko"));
        emailColumn.setCellValueFactory(new PropertyValueFactory("klient_email"));
        miastoColumn.setCellValueFactory(new PropertyValueFactory("klient_miasto"));
        ulicaColumn.setCellValueFactory(new PropertyValueFactory("klient_ulica"));
        nrdomuColumn.setCellValueFactory(new PropertyValueFactory("klient_nr_domu"));
        nrmieszkaniaColumn.setCellValueFactory(new PropertyValueFactory("klient_nr_mieszkania"));
        kodpocztowyColumn.setCellValueFactory(new PropertyValueFactory("klient_kod_pocztowy"));
        editClientTable.getColumns().addAll(idColumn, loginColumn, hasloColumn, imieColumn, nazwiskoColumn, emailColumn, miastoColumn, ulicaColumn, nrdomuColumn, nrmieszkaniaColumn, kodpocztowyColumn);
    }
        editClientTable.getItems().clear();
        editClientTable.getItems().addAll(klientServiceImp.findAll());
    }

    public void filterClients(){
        List<Klient> filtered = editClientTable.getItems();
        List<Klient> tempFilteredClients = new ArrayList<>();
        for(Klient k: filtered){
            tempFilteredClients.add(k);
        }
        filtered = tempFilteredClients.stream()
                .filter(klient -> klient.getKlient_login().equals((filterLogin.getText().isEmpty()?klient.getKlient_login():filterLogin.getText())))
                .filter(klient -> klient.getKlient_imie().equals((filterName.getText().isEmpty()?((klient.getKlient_imie() == null)?filterName.getText():klient.getKlient_imie()):filterName.getText())))
                .filter(klient -> klient.getKlient_nazwisko().equals((filterSecondName.getText().isEmpty()?((klient.getKlient_nazwisko() == null)?filterSecondName.getText():klient.getKlient_nazwisko()):filterSecondName.getText())))
                .filter(klient -> klient.getKlient_miasto().equals((filterCity.getText().isEmpty()?klient.getKlient_miasto():filterCity.getText())) )
                .filter(klient -> klient.getKlient_kod_pocztowy().equals((filterZipCode.getText().isEmpty()?klient.getKlient_kod_pocztowy():filterZipCode.getText())))
                .collect(Collectors.toList());
        editClientTable.getItems().clear();
        editClientTable.getItems().addAll(filtered);
    }

}
