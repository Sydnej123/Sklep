package pl.InternetowySklepMuzyczny.sklep.fxml_controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import pl.InternetowySklepMuzyczny.sklep.models.*;
import pl.InternetowySklepMuzyczny.sklep.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
public class AdminPanelController {

    @FXML
    TitledPane editClientsTab, clientOrderPane, albumsEditPane;

    @FXML
    TableView editClientTable, clientOrderTable, orderDetailsTable, bestClientsTable, albumEditTable;

    @FXML
    TextField loginAddField, passwordAddField, nameAddField, secondnameAddField, emailAddField, cityAddField, streetAddField, houseNumberAddField, flatNumberAddField, zipCodeAddField;

    @FXML
    TextField filterLogin, filterName, filterSecondName, filterCity, filterZipCode;
    @FXML
    Label errorLabel;
    @FXML
    Button filterButton;

    @FXML
    ChoiceBox klientChoise, gatunekTableFilter, zespolTableFilter;


    @Autowired
    ZespolServiceImp zespolServiceImp;

    @Autowired
    PracownikServiceImp pracownikServiceImp;

    @Autowired
    KlientServiceImp klientServiceImp;

    @Autowired
    ZamowienieServiceImp zamowienieServiceImp;

    @Autowired
    Szczegoly_zamowieniaServiceImp szczegoly_zamowieniaServiceImp;
    @Autowired
    AlbumServiceImp albumServiceImp;
    @Autowired
    Gatunek_muzykiServiceImp gatunek_muzykiServiceImp;
    private boolean firstTime= true;
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
                .filter(klient -> filterLogin.getText().isEmpty() || (klient.getKlient_login() == null)? true: (klient.getKlient_login().equals(filterLogin.getText())))
                .filter(klient -> filterName.getText().isEmpty() || (klient.getKlient_imie() == null)? true: (klient.getKlient_imie().equals(filterName.getText())))
                .filter(klient -> filterSecondName.getText().isEmpty() || (klient.getKlient_nazwisko() == null)? true: (klient.getKlient_nazwisko().equals(filterSecondName.getText())))
                .filter(klient -> filterCity.getText().isEmpty() || (klient.getKlient_miasto() == null)? true: (klient.getKlient_miasto().equals(filterCity.getText())))
                .filter(klient -> filterZipCode.getText().isEmpty() || (klient.getKlient_kod_pocztowy() == null)? true: (klient.getKlient_kod_pocztowy().equals(filterZipCode.getText())))
                .collect(Collectors.toList());
        editClientTable.getItems().clear();
        filterZipCode.clear();
        filterCity.clear();
        filterLogin.clear();
        filterSecondName.clear();
        filterName.clear();
        editClientTable.getItems().addAll(filtered);
    }
    public void initializeClientOrder(){

        klientChoise.getItems().clear();
        List<Klient> klients = klientServiceImp.findAll();
        for(Klient klient:klients){
            klientChoise.getItems().add(klient);
        }
        klientChoise.setValue(klients.get(0));
        showResults();
        clientOrderTable.getItems().clear();
        clientOrderTable.getItems().addAll(zamowienieServiceImp.getOrderByClientId(1));

        klientChoise.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Klient temporary = (Klient) klientChoise.getValue();
                showResults();
                clientOrderTable.getItems().clear();
                clientOrderTable.getItems().addAll(zamowienieServiceImp.getOrderByClientId(temporary.getKlient_id()));
            }


        });
        if(firstTime){
            clientOrderTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    Zamowienie temporary = (Zamowienie) newSelection;
                    orderDetailsFill(temporary.getZamowienie_id());
                }
            });
        }
        firstTime = false;

    }
    public void showResults(){
        if(clientOrderTable.getColumns().isEmpty()) {
            TableColumn<Zamowienie, String> idColumn = new TableColumn<>("Id");
            TableColumn<Zamowienie, String> workerColumn = new TableColumn<>("Pracownik");
            TableColumn<Zamowienie, String> clientColumn = new TableColumn<>("Klient");
            TableColumn<Zamowienie, String> valueColumn = new TableColumn<>("Wartość");
            TableColumn<Zamowienie, String> commentColumn = new TableColumn<>("Komentarz");
            TableColumn<Zamowienie, String> dateColumn = new TableColumn<>("Data");
            //  TableColumn<Zamowienie, String> detailsColumn = new TableColumn<>("Szczegóły");

            idColumn.setCellValueFactory(new PropertyValueFactory("zamowienie_id"));
            workerColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getPracownik().getPracownik_imie())));
            clientColumn.setCellValueFactory(c-> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getKlient_id())));
            valueColumn.setCellValueFactory(new PropertyValueFactory("zamowienie_wartosc"));
            commentColumn.setCellValueFactory(new PropertyValueFactory("zamowienie_komentarz"));
            dateColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getZamowienie_data().toString())));
            // detailsColumn.setCellValueFactory(new PropertyValueFactory(""));
            clientOrderTable.getColumns().addAll(idColumn, workerColumn, clientColumn, valueColumn, commentColumn, dateColumn);
        }

    }
    public void orderDetailsFill(Integer orderID){
        if(orderDetailsTable.getColumns().isEmpty()){
            TableColumn<Szczegoly_zamowienia, String> nameColumn = new TableColumn<>("Nazwa albumu");
            TableColumn<Szczegoly_zamowienia, String> countColumn = new TableColumn<>("Ilość");
            orderDetailsTable.getColumns().addAll(nameColumn,countColumn);
            nameColumn.setCellValueFactory(c-> new ReadOnlyStringWrapper(String.valueOf(albumServiceImp.findById(c.getValue().getCompositePrimaryKeySzcze_zam().getAlbum_id()).get(0).getAlbum_nazwa())));
            countColumn.setCellValueFactory(c-> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getSzcze_zam_ilosc())));
        }
        orderDetailsTable.getItems().clear();
        orderDetailsTable.getItems().addAll(szczegoly_zamowieniaServiceImp.findByZamowienieId(orderID));
    }

    public void clientRankingFill(){
        if(bestClientsTable.getColumns().isEmpty()){
            TableColumn<Klient, String> idColumn = new TableColumn<>("Id");
            TableColumn<Klient, String> loginColumn = new TableColumn<>("Login");
            TableColumn<Klient, String> imieColumn = new TableColumn<>("Imie");
            TableColumn<Klient, String> nazwiskoColumn = new TableColumn<>("Nazwisko");
            TableColumn<Klient, String> emailColumn = new TableColumn<>("E-mail");
            TableColumn<Klient, String> orderCountColumn = new TableColumn<>("Ilość zamówień");
            idColumn.setCellValueFactory(new PropertyValueFactory("klient_id"));
            loginColumn.setCellValueFactory(new PropertyValueFactory("klient_login"));
            imieColumn.setCellValueFactory(new PropertyValueFactory("klient_imie"));
            nazwiskoColumn.setCellValueFactory(new PropertyValueFactory("klient_nazwisko"));
            emailColumn.setCellValueFactory(new PropertyValueFactory("klient_email"));
            orderCountColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(zamowienieServiceImp.getOrderCountById(c.getValue().getKlient_id()))));
            bestClientsTable.getColumns().addAll(idColumn, loginColumn,imieColumn, nazwiskoColumn, emailColumn, orderCountColumn);
        }
            bestClientsTable.getItems().clear();
            bestClientsTable.getItems().addAll(klientServiceImp.getOrderedClients());// wszyscy uzytkownicy po najwiekszej ilosc izamowien
    }
    public void showEditAlbumsPane(){
        if(albumEditTable.getColumns().isEmpty()){
            TableColumn<Album, String> idAlbumColumn = new TableColumn<>("Id");
            TableColumn<Album, String> gatunekColumn = new TableColumn<>("Gatunek");
            TableColumn<Album, String> zespolColumn = new TableColumn<>("Zespół");
            TableColumn<Album, String> nazwaColumn = new TableColumn<>("Nazwa albumu");
            TableColumn<Album, String> cenaColumn = new TableColumn<>("Cena");
            TableColumn<Album, String> opisColumn = new TableColumn<>("Opis");
            TableColumn<Album, String> sciezkaDoZdjeciaColumn = new TableColumn<>("Ścieżka");
            TableColumn<Album, String> iloscColumn = new TableColumn<>("Ilość");

            idAlbumColumn.setCellValueFactory(new PropertyValueFactory("album_id"));
            gatunekColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getGatunek_muzyki().getGatunek_nazwa())));
            zespolColumn.setCellValueFactory(C -> new ReadOnlyStringWrapper(String.valueOf(C.getValue().getZespol().getZespol_nazwa())));
            nazwaColumn.setCellValueFactory(new PropertyValueFactory("album_nazwa"));
            cenaColumn.setCellValueFactory(new PropertyValueFactory("album_cena"));
            iloscColumn.setCellValueFactory(new PropertyValueFactory("album_ilosc"));
            opisColumn.setCellValueFactory(new PropertyValueFactory("album_opis"));
            sciezkaDoZdjeciaColumn.setCellValueFactory(new PropertyValueFactory("album_zdjecie_sciezka"));
            albumEditTable.getColumns().addAll(idAlbumColumn,gatunekColumn,zespolColumn,nazwaColumn,cenaColumn,opisColumn,sciezkaDoZdjeciaColumn,iloscColumn);

            gatunekTableFilter.getItems().addAll(gatunek_muzykiServiceImp.findAllGatunek());
            gatunekTableFilter.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Gatunek_muzyki gatunek = (Gatunek_muzyki) gatunekTableFilter.getValue();
                }
            });
            zespolTableFilter.getItems().addAll(zespolServiceImp.findAll());
            zespolTableFilter.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Zespol zespol = (Zespol) zespolTableFilter.getValue();
                }
            });

        }
        albumEditTable.getItems().clear();
        //albumEditTable.getItems().addAll(albumServiceImp.findAll().stream().collect(Collectors.toList()));
        albumServiceImp.findAll().forEach(c -> albumEditTable.getItems().add(c));

        }
        public void filterAlbums(){
        List<Album> filteredAlbums = albumEditTable.getItems();

       // filteredAlbums = filteredAlbums.stream().filter();
        }
    }

