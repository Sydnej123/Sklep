package pl.InternetowySklepMuzyczny.sklep.fxml_controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import pl.InternetowySklepMuzyczny.sklep.StatusZamowienia;
import pl.InternetowySklepMuzyczny.sklep.models.*;
import pl.InternetowySklepMuzyczny.sklep.services.*;

import java.awt.print.Printable;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
public class AdminPanelController {

    @FXML
    TitledPane editClientsTab, clientOrderPane, albumsEditPane;

    @FXML
    TableView editClientTable, clientOrderTable, orderDetailsTable, bestClientsTable, albumEditTable, allAlbumsTable, bandsTable, genreTable, employeesTable, songTable, commentsTable, missingAlbums, ordersToPrepareTable, ordersToPrepareDetails
            ,ordersDoneTable, raportFirstTable, raportSecondTable;

    @FXML
    TextField loginAddField, passwordAddField, nameAddField, secondnameAddField, emailAddField, cityAddField, streetAddField, houseNumberAddField, flatNumberAddField, zipCodeAddField, addGenreField,
            addEmployeeNameField, addEmployeeSecondNameField, addEmployeeVerificationCodeField;

    @FXML
    TextField filterLogin, filterName, filterSecondName, filterCity, filterZipCode, addAlbumNazwa, addAlbumIlosc, addAlbumCena, addAlbumSciezka, addZespolField, addSongNameTextField, addSongDuraction, minValue, maxValue;
    @FXML
    Label errorLabel;
    @FXML
    Button filterButton;

    @FXML
    ChoiceBox klientChoise, gatunekTableFilter, zespolTableFilter, addAlbumGatunek, addAlbumZespol, addEmployeePerrmisionsChoiseBox, addSongAlbumChoiseBox, commentAlbumChoiseBox, orderStatusChoiseBox, employeeChoiseBox, clientChoiseBox, statusChoiseBox;

    @FXML
    TextArea opisTextArea;
    @FXML
    DatePicker dateFrom, dateTo, raportDateFrom, raportDateTo;

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
    @Autowired
    UtworServiceImp utworServiceImp;
    @Autowired
    KomentarzServiceImp komentarzServiceImp;
    private boolean firstTime = true;
    private boolean addedListener = true;

    public void insertNewClient() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Pattern numeric = Pattern.compile("([0-9])+");
        Klient klient = new Klient();
        if (!loginAddField.getText().isEmpty() && !passwordAddField.getText().isEmpty() && !emailAddField.getText().isEmpty() && numeric.matcher(houseNumberAddField.getText()).matches() && numeric.matcher(flatNumberAddField.getText()).matches()) {
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
        } else {
            errorLabel.setText("Błąd wprowadzania danych");
            errorLabel.setTextFill(Color.RED);
            errorLabel.setVisible(true);
        }
    }

    public void insertDataIntoClientTable() {

        if (editClientTable.getColumns().isEmpty()) {
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

    public void filterClients() {
        List<Klient> filtered = editClientTable.getItems();
        List<Klient> tempFilteredClients = new ArrayList<>();
        for (Klient k : filtered) {
            tempFilteredClients.add(k);
        }
        filtered = tempFilteredClients.stream()
                .filter(klient -> filterLogin.getText().isEmpty() || (klient.getKlient_login() == null) ? true : (klient.getKlient_login().equals(filterLogin.getText())))
                .filter(klient -> filterName.getText().isEmpty() || (klient.getKlient_imie() == null) ? true : (klient.getKlient_imie().equals(filterName.getText())))
                .filter(klient -> filterSecondName.getText().isEmpty() || (klient.getKlient_nazwisko() == null) ? true : (klient.getKlient_nazwisko().equals(filterSecondName.getText())))
                .filter(klient -> filterCity.getText().isEmpty() || (klient.getKlient_miasto() == null) ? true : (klient.getKlient_miasto().equals(filterCity.getText())))
                .filter(klient -> filterZipCode.getText().isEmpty() || (klient.getKlient_kod_pocztowy() == null) ? true : (klient.getKlient_kod_pocztowy().equals(filterZipCode.getText())))
                .collect(Collectors.toList());
        editClientTable.getItems().clear();
        filterZipCode.clear();
        filterCity.clear();
        filterLogin.clear();
        filterSecondName.clear();
        filterName.clear();
        editClientTable.getItems().addAll(filtered);
    }

    public void initializeClientOrder() {
        klientChoise.getItems().clear();


        List<Klient> klients = klientServiceImp.findAll();
        for (Klient klient : klients) {
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
        if (firstTime) {
            clientOrderTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    Zamowienie temporary = (Zamowienie) newSelection;
                    orderDetailsFill(temporary.getZamowienie_id());
                }
            });
        }
        firstTime = false;

    }

    public void showResults() {
        if (clientOrderTable.getColumns().isEmpty()) {
            TableColumn<Zamowienie, String> idColumn = new TableColumn<>("Id");
            TableColumn<Zamowienie, String> workerColumn = new TableColumn<>("Pracownik");
            TableColumn<Zamowienie, String> clientColumn = new TableColumn<>("Klient");
            TableColumn<Zamowienie, String> valueColumn = new TableColumn<>("Wartość");
            TableColumn<Zamowienie, String> commentColumn = new TableColumn<>("Komentarz");
            TableColumn<Zamowienie, String> dateColumn = new TableColumn<>("Data");
            //  TableColumn<Zamowienie, String> detailsColumn = new TableColumn<>("Szczegóły");

            idColumn.setCellValueFactory(new PropertyValueFactory("zamowienie_id"));
            workerColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getPracownik().getPracownik_imie())));
            clientColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getKlient().getKlient_id())));
            valueColumn.setCellValueFactory(new PropertyValueFactory("zamowienie_wartosc"));
            commentColumn.setCellValueFactory(new PropertyValueFactory("zamowienie_komentarz"));
            dateColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getZamowienie_data().toString())));
            // detailsColumn.setCellValueFactory(new PropertyValueFactory(""));
            clientOrderTable.getColumns().addAll(idColumn, workerColumn, clientColumn, valueColumn, commentColumn, dateColumn);
        }

    }

    public void orderDetailsFill(Integer orderID) {
        if (orderDetailsTable.getColumns().isEmpty()) {
            TableColumn<Szczegoly_zamowienia, String> nameColumn = new TableColumn<>("Nazwa albumu");
            TableColumn<Szczegoly_zamowienia, String> countColumn = new TableColumn<>("Ilość");
            orderDetailsTable.getColumns().addAll(nameColumn, countColumn);
            nameColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(albumServiceImp.findById(c.getValue().getCompositePrimaryKeySzcze_zam().getAlbum_id()).getAlbum_nazwa())));
            countColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getSzcze_zam_ilosc())));
        }
        orderDetailsTable.getItems().clear();
        orderDetailsTable.getItems().addAll(szczegoly_zamowieniaServiceImp.findByZamowienieId(orderID));
    }

    public void clientRankingFill() {
        if (bestClientsTable.getColumns().isEmpty()) {
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
            bestClientsTable.getColumns().addAll(idColumn, loginColumn, imieColumn, nazwiskoColumn, emailColumn, orderCountColumn);
        }
        bestClientsTable.getItems().clear();
        bestClientsTable.getItems().addAll(klientServiceImp.getOrderedClients());// wszyscy uzytkownicy po najwiekszej ilosc izamowien
    }

    public void showEditAlbumsPane() {
        if (albumEditTable.getColumns().isEmpty()) {
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
            albumEditTable.getColumns().addAll(idAlbumColumn, gatunekColumn, zespolColumn, nazwaColumn, cenaColumn, opisColumn, sciezkaDoZdjeciaColumn, iloscColumn);

            zespolTableFilter.getItems().addAll(zespolServiceImp.findAll());
            zespolTableFilter.getItems().add(null);
            gatunekTableFilter.getItems().addAll(gatunek_muzykiServiceImp.findAllGatunek());
            gatunekTableFilter.getItems().add(null);
            addAlbumZespol.getItems().addAll(zespolServiceImp.findAll());
            addAlbumGatunek.getItems().addAll(gatunek_muzykiServiceImp.findAllGatunek());

        }
        albumEditTable.getItems().clear();
        //albumEditTable.getItems().addAll(albumServiceImp.findAll().stream().collect(Collectors.toList()));
        albumServiceImp.findAll().forEach(c -> albumEditTable.getItems().add(c));

    }

    public void filterAlbums() {
        List<Album> filteredAlbums = albumServiceImp.findAll();
        Zespol temporaryZespol = (Zespol) zespolTableFilter.getValue();
        Gatunek_muzyki temporaryGatunek = (Gatunek_muzyki) gatunekTableFilter.getValue();
        filteredAlbums = filteredAlbums.stream().filter(c -> ((temporaryZespol == null) ? c.getZespol().getZespol_id() : temporaryZespol.getZespol_id()) == c.getZespol().getZespol_id())
                .filter(c -> ((temporaryGatunek == null) ? c.getGatunek_muzyki().getGatunek_id() : temporaryGatunek.getGatunek_id()) == c.getGatunek_muzyki().getGatunek_id())
                .collect(Collectors.toList());
        albumEditTable.getItems().clear();
        albumEditTable.getItems().addAll(filteredAlbums);
    }

    public void addAlbum() {
        if (addAlbumZespol.getValue() != null && addAlbumGatunek.getValue() != null && addAlbumNazwa.getText() != null && addAlbumIlosc.getText() != null && addAlbumCena.getText() != null && addAlbumSciezka.getText() != null) {
            Album albumToAdd = new Album();
            albumToAdd.setGatunek_muzyki((Gatunek_muzyki) addAlbumGatunek.getValue());
            albumToAdd.setZespol((Zespol) addAlbumZespol.getValue());
            albumToAdd.setAlbum_nazwa(addAlbumNazwa.getText());
            albumToAdd.setAlbum_ilosc(Integer.parseInt(addAlbumIlosc.getText()));
            albumToAdd.setAlbum_cena(Float.parseFloat(addAlbumCena.getText()));
            albumToAdd.setAlbum_zdjecie_sciezka(addAlbumSciezka.getText());
            albumToAdd.setAlbum_opis(opisTextArea.getText());
            albumServiceImp.save(albumToAdd);
            addAlbumSciezka.clear();
            addAlbumNazwa.clear();
            addAlbumIlosc.clear();
            addAlbumCena.clear();
            opisTextArea.clear();
            gatunekTableFilter.setValue(null);
            zespolTableFilter.setValue(null);
            albumEditTable.getItems().add(albumToAdd);

        }
    }

    public void listAllAlbums() {
        if (allAlbumsTable.getColumns().isEmpty()) {
            // id Gatunek Zespól NAzwa labumu ilsoc cena
            TableColumn<Album, String> idAlbumColumn = new TableColumn<>("Id");
            TableColumn<Album, String> gatunekColumn = new TableColumn<>("Gatunek");
            TableColumn<Album, String> zespolColumn = new TableColumn<>("Zespół");
            TableColumn<Album, String> nazwaColumn = new TableColumn<>("Nazwa albumu");
            TableColumn<Album, String> cenaColumn = new TableColumn<>("Cena");
            TableColumn<Album, String> iloscColumn = new TableColumn<>("Ilość");

            idAlbumColumn.setCellValueFactory(new PropertyValueFactory("album_id"));
            gatunekColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getGatunek_muzyki().getGatunek_nazwa())));
            zespolColumn.setCellValueFactory(C -> new ReadOnlyStringWrapper(String.valueOf(C.getValue().getZespol().getZespol_nazwa())));
            nazwaColumn.setCellValueFactory(new PropertyValueFactory("album_nazwa"));
            cenaColumn.setCellValueFactory(new PropertyValueFactory("album_cena"));
            iloscColumn.setCellValueFactory(new PropertyValueFactory("album_ilosc"));
            allAlbumsTable.getColumns().addAll(idAlbumColumn, gatunekColumn, zespolColumn, nazwaColumn, cenaColumn, iloscColumn);
        }
        allAlbumsTable.getItems().clear();
        allAlbumsTable.getItems().addAll(albumServiceImp.findAll());
    }

    public void loadBands() {
        if (bandsTable.getColumns().isEmpty()) {
            TableColumn<Zespol, String> bandIdColumn = new TableColumn<>("Id");
            TableColumn<Zespol, String> bandNameColumn = new TableColumn<>("Nazwa zespołu");
            bandIdColumn.setCellValueFactory(new PropertyValueFactory("zespol_id"));
            bandNameColumn.setCellValueFactory(new PropertyValueFactory("zespol_nazwa"));
            bandsTable.getColumns().addAll(bandIdColumn, bandNameColumn);
        }
        bandsTable.getItems().clear();
        bandsTable.getItems().addAll(zespolServiceImp.findAll());


    }

    public void addBand() {
        if (addZespolField.getText() != null) {
            Zespol zespolToAdd = new Zespol();
            zespolToAdd.setZespol_nazwa(addZespolField.getText());
            zespolServiceImp.save(zespolToAdd);
            bandsTable.getItems().add(zespolToAdd);
            addZespolField.clear();
        }

    }

    public void loadGenre() {
        if (genreTable.getColumns().isEmpty()) {
            TableColumn<Zespol, String> genreIdColumn = new TableColumn<>("Id");
            TableColumn<Zespol, String> genreNameColumn = new TableColumn<>("Nazwa zespołu");
            genreIdColumn.setCellValueFactory(new PropertyValueFactory("gatunek_id"));
            genreNameColumn.setCellValueFactory(new PropertyValueFactory("gatunek_nazwa"));
            genreTable.getColumns().addAll(genreIdColumn, genreNameColumn);
        }
        genreTable.getItems().clear();
        genreTable.getItems().addAll(gatunek_muzykiServiceImp.findAllGatunek());

    }

    public void addGenre() {
        if (addGenreField.getText() != null) {
            Gatunek_muzyki gatunek_muzyki = new Gatunek_muzyki();
            gatunek_muzyki.setGatunek_nazwa(addGenreField.getText());
            gatunek_muzykiServiceImp.save(gatunek_muzyki);
            genreTable.getItems().add(gatunek_muzyki);
            addZespolField.clear();
        }

    }

    public void showAllEmployees() {
        if (addEmployeePerrmisionsChoiseBox.getItems().isEmpty()) {
            addEmployeePerrmisionsChoiseBox.getItems().addAll(Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2));
        }

        if (employeesTable.getColumns().isEmpty()) {
            TableColumn<Pracownik, String> employeeIdColumn = new TableColumn<>("Id");
            TableColumn<Pracownik, String> employeeNameColumn = new TableColumn<>("Imie");
            TableColumn<Pracownik, String> employeeSecondNameColumn = new TableColumn<>("Nazwa");
            TableColumn<Pracownik, String> employeePermissionLevelColumn = new TableColumn<>("Poziom uprawnień");
            TableColumn<Pracownik, String> employeeVerificationCodeColumn = new TableColumn<>("Kod weryfikacyjny");

            employeeIdColumn.setCellValueFactory(new PropertyValueFactory("pracownik_id"));
            employeeNameColumn.setCellValueFactory(new PropertyValueFactory("pracownik_imie"));
            employeeSecondNameColumn.setCellValueFactory(new PropertyValueFactory("pracownik_nazwisko"));
            employeePermissionLevelColumn.setCellValueFactory(new PropertyValueFactory("pracownik_poziom_uprawnien"));
            employeeVerificationCodeColumn.setCellValueFactory(new PropertyValueFactory("pracownik_kod"));
            employeesTable.getColumns().addAll(employeeIdColumn, employeeNameColumn, employeeSecondNameColumn, employeePermissionLevelColumn, employeeVerificationCodeColumn);
        }
        employeesTable.getItems().clear();
        employeesTable.getItems().addAll(pracownikServiceImp.findAll());

    }

    public void addEmployee() {
        if (addEmployeeNameField != null && addEmployeeSecondNameField != null && addEmployeeVerificationCodeField != null) {
            Pracownik pracownik = new Pracownik();
            pracownik.setPracownik_imie(addEmployeeNameField.getText());
            pracownik.setPracownik_nazwisko(addEmployeeSecondNameField.getText());
            pracownik.setPracownik_poziom_uprawnien((Integer) addEmployeePerrmisionsChoiseBox.getValue());
            pracownik.setPracownik_kod(addEmployeeVerificationCodeField.getText());
            pracownikServiceImp.save(pracownik);
            employeesTable.getItems().add(pracownik);
            addEmployeeVerificationCodeField.clear();
            addEmployeeSecondNameField.clear();
            addEmployeeNameField.clear();
        }

    }

    public void addSong() {
        if (!addSongNameTextField.getText().isEmpty() && !addSongDuraction.getText().isEmpty()) {
            Utwor utwor = new Utwor();
            utwor.setUtwor_nazwa(addSongNameTextField.getText());
            String timeToParse = addSongDuraction.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            Date date = null;
            Time time = null;
            try {
                date = sdf.parse(timeToParse);
            } catch (ParseException e) {
                date = null;
            }
            time = new Time(date.getTime());
            utwor.setUtwor_czas_trwania(time);
            Album albumId = (Album) addSongAlbumChoiseBox.getValue();
            utwor.setAlbum_id(albumId.getAlbum_id());
            songTable.getItems().add(utwor);
            utworServiceImp.save(utwor);
        }
    }

    public void showSongs() {
        if (addSongAlbumChoiseBox.getItems().isEmpty()) {
            addSongAlbumChoiseBox.getItems().addAll(albumServiceImp.findAll());
        }
        if (songTable.getColumns().isEmpty()) {
            TableColumn<Utwor, String> songIdColumn = new TableColumn<>("ID");
            TableColumn<Utwor, String> songAlbumNameColumn = new TableColumn<>("Album");
            TableColumn<Utwor, String> songNameColumn = new TableColumn<>("Nazwa utworu");
            TableColumn<Utwor, String> songDuration = new TableColumn<>("Czas trwania");

            songIdColumn.setCellValueFactory(new PropertyValueFactory("utwor_id"));
            songNameColumn.setCellValueFactory(new PropertyValueFactory("utwor_nazwa"));
            songDuration.setCellValueFactory(new PropertyValueFactory("utwor_czas_trwania"));
            songAlbumNameColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(albumServiceImp.findById(c.getValue().getAlbum_id()).getAlbum_nazwa())));
            songTable.getColumns().addAll(songIdColumn, songAlbumNameColumn, songNameColumn, songDuration);
        }
        songTable.getItems().clear();
        songTable.getItems().addAll(utworServiceImp.findAll());
    }

    public void showComments() {
        if (commentAlbumChoiseBox.getItems().isEmpty()) {
            commentAlbumChoiseBox.getItems().addAll(albumServiceImp.findAll());
        }
        if (commentsTable.getColumns().isEmpty()) {
            TableColumn<Komentarz, String> commentId = new TableColumn<>("ID");
            TableColumn<Komentarz, String> commentAlbumName = new TableColumn<>("Album");
            TableColumn<Komentarz, String> commentLogin = new TableColumn<>("Login");
            TableColumn<Komentarz, String> commentContent = new TableColumn<>("Treść");
            TableColumn<Komentarz, String> commentRate = new TableColumn<>("Ocena");
            TableColumn<Komentarz, String> commentDate = new TableColumn<>("Data");

            commentId.setCellValueFactory(new PropertyValueFactory("komentarz_id"));
            commentAlbumName.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(albumServiceImp.findById(c.getValue().getAlbum().getAlbum_id()).getAlbum_nazwa())));
            commentLogin.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(klientServiceImp.findLoginByID(c.getValue().getKlient().getKlient_id()))));
            commentContent.setCellValueFactory(new PropertyValueFactory("komentarz_tresc"));
            commentRate.setCellValueFactory(new PropertyValueFactory("komentarz_ocena"));
            commentDate.setCellValueFactory(new PropertyValueFactory("komentarz_data"));
            commentsTable.getColumns().addAll(commentId, commentAlbumName, commentLogin, commentContent, commentRate, commentDate);
        }
        commentsTable.getItems().clear();
        commentsTable.getItems().addAll(komentarzServiceImp.findAll());
    }

    public void filterComment() {
        if (commentAlbumChoiseBox.getValue() != null) {
            Album filterByAlbum = (Album) commentAlbumChoiseBox.getValue();
            commentsTable.getItems().clear();
            commentsTable.getItems().addAll(komentarzServiceImp.findByAlbumId(filterByAlbum.getAlbum_id()));
        }
    }
    public void showMissingAlbums() {
        if (missingAlbums.getColumns().isEmpty()) {
            // id Gatunek Zespól NAzwa labumu ilsoc cena
            TableColumn<Album, String> idAlbumColumn = new TableColumn<>("Id");
            TableColumn<Album, String> nazwaColumn = new TableColumn<>("Nazwa albumu");
            TableColumn<Album, String> iloscColumn = new TableColumn<>("Ilość");
            idAlbumColumn.setCellValueFactory(new PropertyValueFactory("album_id"));
            nazwaColumn.setCellValueFactory(new PropertyValueFactory("album_nazwa"));
            iloscColumn.setCellValueFactory(new PropertyValueFactory("album_ilosc"));
            missingAlbums.getColumns().addAll(idAlbumColumn, nazwaColumn, iloscColumn);
        }
        missingAlbums.getItems().clear();
        missingAlbums.getItems().addAll(albumServiceImp.missingAlbums());
    }
    public void showOrdersToPrepare(){
        if(ordersToPrepareTable.getColumns().isEmpty()){
            orderStatusChoiseBox.getItems().addAll("Zrealizowane","Odrzucone");
            orderStatusChoiseBox.setValue("Zrealizowane");
            TableColumn<Zamowienie, String> orderIdColumn = new TableColumn<>("ID");
            TableColumn<Zamowienie, String> orderEmployeeColumn = new TableColumn<>("Pracownik");
            TableColumn<Zamowienie, String> clientColumn = new TableColumn<>("Klient");
            TableColumn<Zamowienie, String> valueColumn = new TableColumn<>("Wartość");
            TableColumn<Zamowienie, String> commentColumn = new TableColumn<>("Komentarz");
            TableColumn<Zamowienie, String> dateColumn = new TableColumn<>("Data");
            TableColumn<Zamowienie, String> statusColumn = new TableColumn<>("Status");

            orderIdColumn.setCellValueFactory(new PropertyValueFactory("zamowienie_id"));
            orderEmployeeColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getPracownik().getPracownik_id())));
            clientColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(klientServiceImp.getKlientNameById(c.getValue().getKlient().getKlient_id()))));
            valueColumn.setCellValueFactory(new PropertyValueFactory("zamowienie_wartosc"));
            commentColumn.setCellValueFactory(new PropertyValueFactory("zamowienie_komentarz"));
            dateColumn.setCellValueFactory(new PropertyValueFactory("zamowienie_data"));
            statusColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(StatusZamowienia.getStatusById(c.getValue().getZamowienie_status()))));
            ordersToPrepareTable.getColumns().addAll(orderIdColumn, orderEmployeeColumn, clientColumn, valueColumn, commentColumn, dateColumn, statusColumn);
        }
        ordersToPrepareTable.getItems().clear();
        ordersToPrepareTable.getItems().addAll(zamowienieServiceImp.getOrderByStatus(0));
        if (addedListener) {
            ordersToPrepareTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    Zamowienie temporary = (Zamowienie) newSelection;
                    orderToPrepareDetailsFill(temporary.getZamowienie_id());
                }
            });
        }
        addedListener = false;
    }
    public void orderToPrepareDetailsFill(Integer orderID) {
        if (ordersToPrepareDetails.getColumns().isEmpty()) {
            TableColumn<Szczegoly_zamowienia, String> nameColumn = new TableColumn<>("Nazwa albumu");
            TableColumn<Szczegoly_zamowienia, String> countColumn = new TableColumn<>("Ilość");
            ordersToPrepareDetails.getColumns().addAll(nameColumn, countColumn);
            nameColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(albumServiceImp.findById(c.getValue().getCompositePrimaryKeySzcze_zam().getAlbum_id()).getAlbum_nazwa())));
            countColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getSzcze_zam_ilosc())));
        }
        ordersToPrepareDetails.getItems().clear();
        ordersToPrepareDetails.getItems().addAll(szczegoly_zamowieniaServiceImp.findByZamowienieId(orderID));
    }
    public void changeOrderStatus(){
        String status  = (String) orderStatusChoiseBox.getValue();
        Zamowienie orderToChangeStatus = (Zamowienie) ordersToPrepareTable.getSelectionModel().getSelectedItem();
        if (status.equals("Zrealizowane")) {
            orderToChangeStatus.setZamowienie_status(1);
        }else{
            orderToChangeStatus.setZamowienie_status(2);
        }
            zamowienieServiceImp.save(orderToChangeStatus);
        ordersToPrepareTable.getItems().clear();
        ordersToPrepareTable.getItems().addAll(zamowienieServiceImp.getOrderByStatus(0));
    }
    public void showOrdersDone(){
        if(ordersDoneTable.getColumns().isEmpty()){
            employeeChoiseBox.getItems().addAll(pracownikServiceImp.findAll());
            employeeChoiseBox.getItems().add(null);
            clientChoiseBox.getItems().addAll(klientServiceImp.findAll());
            clientChoiseBox.getItems().add(null);
            statusChoiseBox.getItems().addAll(new String[]{"Zrealizowane", "Odrzucone"});
            statusChoiseBox.setValue("Zrealizowane");
            TableColumn<Zamowienie, String> orderIdColumn = new TableColumn<>("ID");
            TableColumn<Zamowienie, String> orderEmployeeColumn = new TableColumn<>("Pracownik");
            TableColumn<Zamowienie, String> clientColumn = new TableColumn<>("Klient");
            TableColumn<Zamowienie, String> valueColumn = new TableColumn<>("Wartość");
            TableColumn<Zamowienie, String> commentColumn = new TableColumn<>("Komentarz");
            TableColumn<Zamowienie, String> dateColumn = new TableColumn<>("Data");
            TableColumn<Zamowienie, String> statusColumn = new TableColumn<>("Status");

            orderIdColumn.setCellValueFactory(new PropertyValueFactory("zamowienie_id"));
            orderEmployeeColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getPracownik().getPracownik_id())));
            clientColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(klientServiceImp.getKlientNameById(c.getValue().getKlient().getKlient_id()))));
            valueColumn.setCellValueFactory(new PropertyValueFactory("zamowienie_wartosc"));
            commentColumn.setCellValueFactory(new PropertyValueFactory("zamowienie_komentarz"));
            dateColumn.setCellValueFactory(new PropertyValueFactory("zamowienie_data"));
            statusColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(StatusZamowienia.getStatusById(c.getValue().getZamowienie_status()))));
            ordersDoneTable.getColumns().addAll(orderIdColumn, orderEmployeeColumn, clientColumn, valueColumn, commentColumn, dateColumn, statusColumn);
        }
        ordersDoneTable.getItems().clear();
        ordersDoneTable.getItems().addAll(zamowienieServiceImp.getOrderByStatus(1));
        ordersDoneTable.getItems().addAll(zamowienieServiceImp.getOrderByStatus(2));


    }
    public void filterOrdersDone(){
        LocalDate localDate = dateFrom.getValue();
        LocalDate tolocalDate = dateTo.getValue();
        ordersDoneTable.getItems().clear();
        Pracownik pracownik = (Pracownik) employeeChoiseBox.getValue();
        Klient klient = (Klient) clientChoiseBox.getValue();
        String value = (String) statusChoiseBox.getValue();
        //ordersDoneTable.getItems().addAll(zamowienieServiceImp.getOrderByStatus(0));
        List<Zamowienie> filteredZamowienie = new ArrayList<>();
        filteredZamowienie.addAll(zamowienieServiceImp.getOrderByStatus(1));
        filteredZamowienie.addAll(zamowienieServiceImp.getOrderByStatus(2));
        filteredZamowienie = filteredZamowienie.stream()
                .filter(c-> (klient != null)?(c.getKlient().getKlient_id() == klient.getKlient_id()):true)
                .filter(c -> (pracownik != null)?(c.getPracownik().getPracownik_id() == pracownik.getPracownik_id()):true)
                .filter( c-> (!minValue.getText().isEmpty())?(c.getZamowienie_wartosc() >= Double.parseDouble(minValue.getText())):true)
                .filter( c-> (!maxValue.getText().isEmpty())?(c.getZamowienie_wartosc() <= Double.parseDouble(maxValue.getText())):true)
                .filter(c -> (dateFrom.getValue() != null)?(c.getZamowienie_data().after(java.util.Date.from(dateFrom.getValue().atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))):true)
                .filter(c -> (dateTo.getValue() != null)?(c.getZamowienie_data().before(java.util.Date.from(dateTo.getValue().atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))):true)
                .filter(c->(value.isEmpty()?true:(value.equals("Zrealizowane")?c.getZamowienie_status()==1:c.getZamowienie_status()==2)) )
                .collect(Collectors.toList());
        ordersDoneTable.getItems().clear();
        ordersDoneTable.getItems().addAll(filteredZamowienie);
    }
    public void showRaport(){
        LocalDate localDate = raportDateFrom.getValue();
        LocalDate tolocalDate =raportDateTo.getValue();
        Date dateFrom = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date dateTo = Date.from(tolocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        if(raportFirstTable.getColumns().isEmpty() && raportSecondTable.getColumns().isEmpty()){
            TableColumn<String, String> ordersCount = new TableColumn<>("Ilość zamówień");
            TableColumn<String, String> ordersValueCount = new TableColumn<>("Łączna wartość zamówień");
            TableColumn<String, String> albumsSold = new TableColumn<>("Sprzedanych albumów");
            TableColumn<String, String> avarageNumberOfAlbums = new TableColumn<>("Średnia ilość albumów na zamówienie");
            TableColumn<String, String> avarageOrderValue = new TableColumn<>("Średnia wartość zamówienia");
            TableColumn<String, String> bestSellerAlbum = new TableColumn<>("Najlepiej sprzedający się album");
            TableColumn<String, String> ordersCanceled = new TableColumn<>("Ilość odrzuconych zamówień");
            TableColumn<String, String> bestEmployee = new TableColumn<>("Pracownik z największa ilością zrealizowanyc zamówień");

            ordersCount.setCellValueFactory(c-> new ReadOnlyStringWrapper(String.valueOf(zamowienieServiceImp.getOrdersCount(Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), Date.from(tolocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())))));
            ordersValueCount.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.format("%.2f", zamowienieServiceImp.sumValue(dateFrom, dateTo))));
            albumsSold.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(szczegoly_zamowieniaServiceImp.getAlbumsCount(dateFrom, dateTo))));
            avarageNumberOfAlbums.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.format("%.2f", zamowienieServiceImp.avarageAlbumsPerOrder(dateFrom, dateTo))));
            avarageOrderValue.setCellValueFactory(c-> new ReadOnlyStringWrapper(String.format("%.2f", zamowienieServiceImp.avarageOrderValue(dateFrom, dateTo))));
            ordersCanceled.setCellValueFactory(c->new ReadOnlyStringWrapper(String.valueOf(zamowienieServiceImp.canceledOrders(dateFrom, dateTo))));
            bestEmployee.setCellValueFactory(c-> new ReadOnlyStringWrapper(pracownikServiceImp.getBestSeller(dateFrom, dateTo)));
            bestSellerAlbum.setCellValueFactory(c-> new ReadOnlyStringWrapper(szczegoly_zamowieniaServiceImp.getBestSellerAlbumName(dateFrom, dateTo)));
            raportFirstTable.getColumns().addAll(ordersCount, ordersValueCount, albumsSold, avarageNumberOfAlbums);
            raportSecondTable.getColumns().addAll(avarageOrderValue, bestSellerAlbum, ordersCanceled, bestEmployee);

        }
        raportFirstTable.getItems().clear();
        raportFirstTable.getItems().add(null);
        raportSecondTable.getItems().clear();
        raportSecondTable.getItems().add(null);



    }
    public void editClient(){

        if(editClientTable.getSelectionModel().getSelectedItem() != null){
            Klient klientToEdit = (Klient) editClientTable.getSelectionModel().getSelectedItem();
            Dialog<Klient> dialog = new Dialog<>();
            dialog.setTitle("Edytuj klienta");
            dialog.setResizable(false);
            dialog.setWidth(850.0);
            dialog.setHeight(200.0);
            Label label1 = new Label("Login: ");
            Label label2 = new Label("Hasło: ");
            Label label3 = new Label("Imie: ");
            Label label4 = new Label("Nazwisko: ");
            Label label5 = new Label("E-mail: ");
            Label label6 = new Label("Miasto: ");
            Label label7 = new Label("Ulica:  ");
            Label label8 = new Label("Numer domu:  ");
            Label label9 = new Label("Numer mieszkania:  ");
            Label label10 = new Label("Kod pocztowy: ");

            TextField text1 = new TextField();
            TextField text2 = new TextField();
            TextField text3 = new TextField();
            TextField text4 = new TextField();
            TextField text5 = new TextField();
            TextField text6 = new TextField();
            TextField text7 = new TextField();
            TextField text8 = new TextField();
            TextField text9 = new TextField();
            TextField text10 = new TextField();
            text1.setText(klientToEdit.getKlient_login());
            text2.setText(klientToEdit.getKlient_haslo());
            text3.setText(klientToEdit.getKlient_imie());
            text4.setText(klientToEdit.getKlient_nazwisko());
            text5.setText(klientToEdit.getKlient_email());
            text6.setText(klientToEdit.getKlient_miasto());
            text7.setText(klientToEdit.getKlient_ulica());
            text8.setText(klientToEdit.getKlient_nr_domu().toString());
            text9.setText(klientToEdit.getKlient_nr_mieszkania().toString());
            text10.setText(klientToEdit.getKlient_kod_pocztowy());
            GridPane grid = new GridPane();
            grid.setPrefWidth(1000);
            grid.add(label1, 1, 1);
            grid.add(text1, 1, 2);
            grid.add(label2, 2, 1);
            grid.add(text2, 2, 2);

            grid.add(label3, 3, 1);
            grid.add(text3, 3, 2);

            grid.add(label4, 4, 1);
            grid.add(text4, 4, 2);
            grid.add(label5, 5, 1);
            grid.add(text5, 5, 2);
            grid.add(label6, 6, 1);
            grid.add(text6, 6, 2);
            grid.add(label7, 7, 1);
            grid.add(text7, 7, 2);
            grid.add(label8, 8, 1);
            grid.add(text8, 8, 2);
            grid.add(label9, 9, 1);
            grid.add(text9, 9, 2);
            grid.add(label10, 10, 1);
            grid.add(text10, 10, 2);
            grid.setMaxSize(800,200);
            dialog.getDialogPane().setContent(grid);


            ButtonType buttonTypeOk = new ButtonType("Edytuj", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonCancel = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
            dialog.getDialogPane().getButtonTypes().add(buttonCancel);

            dialog.setResultConverter(new Callback<ButtonType, Klient>() {
                @Override
                public Klient call(ButtonType b) {

                    if (b == buttonTypeOk) {
                        editClientTable.getItems().remove(klientToEdit);
                        klientToEdit.setKlient_login(text1.getText());
                        klientToEdit.setKlient_haslo(text2.getText());
                        klientToEdit.setKlient_imie(text3.getText());
                        klientToEdit.setKlient_nazwisko(text4.getText());
                        klientToEdit.setKlient_email(text5.getText());
                        klientToEdit.setKlient_miasto(text6.getText());
                        klientToEdit.setKlient_ulica(text7.getText());
                        klientToEdit.setKlient_nr_domu(Integer.parseInt(text8.getText()));
                        klientToEdit.setKlient_nr_mieszkania(Integer.parseInt(text9.getText()));
                        klientToEdit.setKlient_kod_pocztowy(text10.getText());
                        klientServiceImp.save(klientToEdit);
                        editClientTable.getItems().add(klientToEdit);
                        return null;
                    }

                    return null;
                }
            });

            Optional<Klient> result = dialog.showAndWait();

        }

    }
    public void deleteClient(){
        if(editClientTable.getSelectionModel().getSelectedItem() != null) {
            Klient klientToEdit = (Klient) editClientTable.getSelectionModel().getSelectedItem();
            Dialog<Klient> dialog = new Dialog<>();
            dialog.setTitle("Usuń klienta");
            dialog.setResizable(false);
            dialog.setContentText("Klient zostanie stale usunięty z bazy danych. Czy na pewno chcesz usunąć klienta?");
            ButtonType buttonTypeOk = new ButtonType("Usuń", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonCancel = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
            dialog.getDialogPane().getButtonTypes().add(buttonCancel);

            dialog.setResultConverter(new Callback<ButtonType, Klient>() {
                @Override
                public Klient call(ButtonType b) {

                    if (b == buttonTypeOk) {
                        editClientTable.getItems().remove(klientToEdit);
                        klientServiceImp.delete(klientToEdit);
                        return null;
                    }

                    return null;
                }
            });

            Optional<Klient> result = dialog.showAndWait();

        }
    }
    public void editAlbum() {
        if (albumEditTable.getSelectionModel().getSelectedItem() != null) {
            Album albumToEdit =(Album) albumEditTable.getSelectionModel().getSelectedItem();
            Dialog<Album> dialog = new Dialog<>();
            dialog.setTitle("Edytuj album");
            dialog.setResizable(false);
            dialog.setWidth(850.0);
            dialog.setHeight(200.0);
            Label label1 = new Label("Gatunek: ");
            Label label2 = new Label("Zespol: ");
            Label label3 = new Label("Nazwa albmu: ");
            Label label4 = new Label("Cena: ");
            Label label5 = new Label("Opis: ");
            Label label6 = new Label("Zdjęcie_scieżka: ");

            ChoiceBox<Gatunek_muzyki> gatunek = new ChoiceBox<>();
            gatunek.getItems().addAll(gatunek_muzykiServiceImp.findAllGatunek());
            ChoiceBox<Zespol> zespol = new ChoiceBox<>();
            zespol.getItems().addAll(zespolServiceImp.findAll());
            gatunek.setValue(albumToEdit.getGatunek_muzyki());
            zespol.setValue(albumToEdit.getZespol());
            TextField text3 = new TextField();
            TextField text4 = new TextField();
            TextField text5 = new TextField();
            TextField text6 = new TextField();
            text3.setText(albumToEdit.getAlbum_nazwa());
            text4.setText(albumToEdit.getAlbum_cena()+"");
            text5.setText(albumToEdit.getAlbum_opis());
            text6.setText(albumToEdit.getAlbum_zdjecie_sciezka());
            GridPane grid = new GridPane();
            grid.setPrefWidth(1000);
            grid.add(label1, 1, 1);
            grid.add(gatunek, 1, 2);
            grid.add(label2, 2, 1);
            grid.add(zespol, 2, 2);
            grid.add(label3, 3, 1);
            grid.add(text3, 3, 2);
            grid.add(label4, 4, 1);
            grid.add(text4, 4, 2);
            grid.add(label5, 5, 1);
            grid.add(text5, 5, 2);
            grid.add(label6, 6, 1);
            grid.add(text6, 6, 2);
            grid.setMaxSize(800, 200);
            dialog.getDialogPane().setContent(grid);


            ButtonType buttonTypeOk = new ButtonType("Edytuj", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonCancel = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
            dialog.getDialogPane().getButtonTypes().add(buttonCancel);

            dialog.setResultConverter(new Callback<ButtonType, Album>() {
                @Override
                public Album call(ButtonType b) {

                    if (b == buttonTypeOk) {
                        albumEditTable.getItems().remove(albumToEdit);
                        albumToEdit.setGatunek_muzyki(gatunek.getValue());
                        albumToEdit.setZespol(zespol.getValue());
                        albumToEdit.setAlbum_nazwa(text3.getText());
                        albumToEdit.setAlbum_cena(Float.parseFloat(text4.getText()));
                        albumToEdit.setAlbum_opis(text5.getText());
                        albumToEdit.setAlbum_zdjecie_sciezka(text6.getText());
                        albumEditTable.getItems().add(albumToEdit);
                        albumServiceImp.save(albumToEdit);
                        return null;
                    }

                    return null;
                }
            });

            Optional<Album> result = dialog.showAndWait();

        }
    }
    public void deleteAlbum(){
        if(albumEditTable.getSelectionModel().getSelectedItem() != null) {
            Album albumToEdit = (Album) albumEditTable.getSelectionModel().getSelectedItem();
            Dialog<Album> dialog = new Dialog<>();
            dialog.setTitle("Usuń album");
            dialog.setResizable(false);
            dialog.setContentText("Album zostanie stale usunięty z bazy danych. Czy na pewno chcesz usunąć album?");
            ButtonType buttonTypeOk = new ButtonType("Usuń", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonCancel = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
            dialog.getDialogPane().getButtonTypes().add(buttonCancel);

            dialog.setResultConverter(new Callback<ButtonType, Album>() {
                @Override
                public Album call(ButtonType b) {

                    if (b == buttonTypeOk) {
                        albumEditTable.getItems().remove(albumToEdit);
                        albumServiceImp.delete(albumToEdit);
                        return null;
                    }

                    return null;
                }
            });

            Optional<Album> result = dialog.showAndWait();

        }
    }
    public void editBand(){
        if (bandsTable.getSelectionModel().getSelectedItem() != null) {
            Zespol zespolToEdit = (Zespol) bandsTable.getSelectionModel().getSelectedItem();
            Dialog<Zespol> dialog = new Dialog<>();
            dialog.setTitle("Edytuj zespol");
            dialog.setResizable(false);
            Label label1 = new Label("Zespol: ");



            TextField text1 = new TextField();
            text1.setText(zespolToEdit.getZespol_nazwa());
            GridPane grid = new GridPane();
            grid.setPrefWidth(300);
            grid.add(label1, 1, 1);
            grid.add(text1, 1, 2);
            grid.setMaxSize(800, 200);
            dialog.getDialogPane().setContent(grid);


            ButtonType buttonTypeOk = new ButtonType("Edytuj", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonCancel = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
            dialog.getDialogPane().getButtonTypes().add(buttonCancel);

            dialog.setResultConverter(new Callback<ButtonType, Zespol>() {
                @Override
                public Zespol call(ButtonType b) {

                    if (b == buttonTypeOk) {
                      bandsTable.getItems().remove(zespolToEdit);
                      zespolToEdit.setZespol_nazwa(text1.getText());
                      bandsTable.getItems().add(zespolToEdit);
                      zespolServiceImp.save(zespolToEdit);
                        return null;
                    }

                    return null;
                }
            });

            Optional<Zespol> result = dialog.showAndWait();

        }

    }
    public void deleteBand(){
        if(bandsTable.getSelectionModel().getSelectedItem() != null) {
            Zespol zespolToEdit = (Zespol) bandsTable.getSelectionModel().getSelectedItem();
            Dialog<Zespol> dialog = new Dialog<>();
            dialog.setTitle("Usuń zespół");
            dialog.setResizable(false);
            dialog.setContentText("Zespół zostanie stale usunięty z bazy danych. Czy na pewno chcesz usunąć zespół?");
            ButtonType buttonTypeOk = new ButtonType("Usuń", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonCancel = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
            dialog.getDialogPane().getButtonTypes().add(buttonCancel);

            dialog.setResultConverter(new Callback<ButtonType, Zespol>() {
                @Override
                public Zespol call(ButtonType b) {

                    if (b == buttonTypeOk) {
                        bandsTable.getItems().remove(zespolToEdit);
                        zespolServiceImp.delete(zespolToEdit);
                        return null;
                    }

                    return null;
                }
            });

            Optional<Zespol> result = dialog.showAndWait();

        }
    }
    public void editSong(){
        if (songTable.getSelectionModel().getSelectedItem() != null) {
            Utwor utworToEdit = (Utwor) songTable.getSelectionModel().getSelectedItem();
            Dialog<Utwor> dialog = new Dialog<>();
            dialog.setTitle("Edytuj utwór");
            dialog.setResizable(false);
            Label label1 = new Label("Nazwa utworu: ");
            Label label2 = new Label("Czas trwania: ");

            TextField text1 = new TextField();
            TextField text2 = new TextField();

            text1.setText(utworToEdit.getUtwor_nazwa());
            text2.setText(utworToEdit.getUtwor_czas_trwania().toString());
            GridPane grid = new GridPane();
            grid.setPrefWidth(300);
            grid.add(label1, 1, 1);
            grid.add(text1, 1, 2);
            grid.add(label2, 2, 1);
            grid.add(text2, 2, 2);
            grid.setMaxSize(800, 200);
            dialog.getDialogPane().setContent(grid);


            ButtonType buttonTypeOk = new ButtonType("Edytuj", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonCancel = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
            dialog.getDialogPane().getButtonTypes().add(buttonCancel);

            dialog.setResultConverter(new Callback<ButtonType, Utwor>() {
                @Override
                public Utwor call(ButtonType b) {

                    if (b == buttonTypeOk) {
                        songTable.getItems().remove(utworToEdit);
                        utworToEdit.setUtwor_nazwa(text1.getText());
                        DateFormat formatter = new SimpleDateFormat("hh:mm:ss");
                        try {
                            utworToEdit.setUtwor_czas_trwania(new Time(formatter.parse(text2.getText()).getTime()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        songTable.getItems().add(utworToEdit);
                        utworServiceImp.save(utworToEdit);
                        return null;
                    }

                    return null;
                }
            });

            Optional <Utwor> result = dialog.showAndWait();

        }
    }
    public void deleteSong(){
        if(songTable.getSelectionModel().getSelectedItem() != null) {
            Utwor utworToEdit = (Utwor) songTable.getSelectionModel().getSelectedItem();
            Dialog<Utwor> dialog = new Dialog<>();
            dialog.setTitle("Usuń utwór");
            dialog.setResizable(false);
            dialog.setContentText("Utwór  zostanie stale usunięty z bazy danych. Czy na pewno chcesz usunąć utwór?");
            ButtonType buttonTypeOk = new ButtonType("Usuń", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonCancel = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
            dialog.getDialogPane().getButtonTypes().add(buttonCancel);

            dialog.setResultConverter(new Callback<ButtonType, Utwor>() {
                @Override
                public Utwor call(ButtonType b) {

                    if (b == buttonTypeOk) {
                        songTable.getItems().remove(utworToEdit);
                        utworServiceImp.delete(utworToEdit);
                        return null;
                    }

                    return null;
                }
            });

            Optional<Utwor> result = dialog.showAndWait();
        }
    }
    public void editGenre(){
        if (genreTable.getSelectionModel().getSelectedItem() != null) {
            Gatunek_muzyki genreToEdit = (Gatunek_muzyki) genreTable.getSelectionModel().getSelectedItem();
            Dialog<Gatunek_muzyki> dialog = new Dialog<>();
            dialog.setTitle("Edytuj gatunek muzyki");
            dialog.setResizable(false);
            Label label1 = new Label("Gatunek muzyki: ");
            TextField text1 = new TextField();


            text1.setText(genreToEdit.getGatunek_nazwa());
            GridPane grid = new GridPane();
            grid.setPrefWidth(300);
            grid.add(label1, 1, 1);
            grid.add(text1, 1, 2);
            grid.setMaxSize(800, 200);
            dialog.getDialogPane().setContent(grid);


            ButtonType buttonTypeOk = new ButtonType("Edytuj", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonCancel = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
            dialog.getDialogPane().getButtonTypes().add(buttonCancel);

            dialog.setResultConverter(new Callback<ButtonType, Gatunek_muzyki>() {
                @Override
                public Gatunek_muzyki call(ButtonType b) {

                    if (b == buttonTypeOk) {
                        songTable.getItems().remove(genreToEdit);
                        genreToEdit.setGatunek_nazwa(text1.getText());
                        songTable.getItems().add(genreToEdit);
                        gatunek_muzykiServiceImp.save(genreToEdit);
                        return null;
                    }

                    return null;
                }
            });

            dialog.showAndWait();

        }
    }
    public void deleteGenre(){
        if(genreTable.getSelectionModel().getSelectedItem() != null) {
            Gatunek_muzyki genreToEdit = (Gatunek_muzyki) genreTable.getSelectionModel().getSelectedItem();
            Dialog<Gatunek_muzyki> dialog = new Dialog<>();
            dialog.setTitle("Usuń gatunek muzyki");
            dialog.setResizable(false);
            dialog.setContentText("Gatunek muzyki  zostanie stale usunięty z bazy danych. Czy na pewno chcesz usunąć gatunek muzyki?");
            ButtonType buttonTypeOk = new ButtonType("Usuń", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonCancel = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
            dialog.getDialogPane().getButtonTypes().add(buttonCancel);

            dialog.setResultConverter(new Callback<ButtonType, Gatunek_muzyki>() {
                @Override
                public Gatunek_muzyki call(ButtonType b) {

                    if (b == buttonTypeOk) {
                        genreTable.getItems().remove(genreToEdit);
                        gatunek_muzykiServiceImp.delete(genreToEdit);
                        return null;
                    }

                    return null;
                }
            });
            dialog.showAndWait();
        }
    }
    public void editEmployee(){
        if (employeesTable.getSelectionModel().getSelectedItem() != null) {
            Pracownik employeetoEdit = (Pracownik) employeesTable.getSelectionModel().getSelectedItem();
            Dialog<Pracownik> dialog = new Dialog<>();
            dialog.setTitle("Edytuj pracownika");
            dialog.setResizable(false);
            Label label1 = new Label("Imie: ");
            Label label2 = new Label("Nazwisko: ");
            Label label3 = new Label("Kod dostępu: ");
            Label label4 = new Label("Poziom uprawnień: ");
            TextField text1 = new TextField();
            TextField text2 = new TextField();
            TextField text3 = new TextField();
            TextField text4 = new TextField();


            text1.setText(employeetoEdit.getPracownik_imie());
            text1.setText(employeetoEdit.getPracownik_nazwisko());
            text1.setText(employeetoEdit.getPracownik_kod());
            text1.setText(Integer.toString(employeetoEdit.getPracownik_poziom_uprawnien()));
            GridPane grid = new GridPane();
            grid.setPrefWidth(800);
            grid.add(label1, 1, 1);
            grid.add(text1, 1, 2);
            grid.add(label2, 2, 1);
            grid.add(text2, 2, 2);
            grid.add(label3, 3, 1);
            grid.add(text3, 3, 2);
            grid.add(label4, 4, 1);
            grid.add(text4, 4, 2);
            grid.setMaxSize(800, 200);
            dialog.getDialogPane().setContent(grid);


            ButtonType buttonTypeOk = new ButtonType("Edytuj", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonCancel = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
            dialog.getDialogPane().getButtonTypes().add(buttonCancel);

            dialog.setResultConverter(new Callback<ButtonType, Pracownik>() {
                @Override
                public Pracownik call(ButtonType b) {

                    if (b == buttonTypeOk) {
                        employeesTable.getItems().remove(employeetoEdit);
                        employeetoEdit.setPracownik_imie(text1.getText());
                        employeetoEdit.setPracownik_nazwisko(text2.getText());
                        employeetoEdit.setPracownik_kod(text3.getText());
                        employeetoEdit.setPracownik_poziom_uprawnien(Integer.parseInt(text4.getText()));
                        employeesTable.getItems().add(employeetoEdit);
                        pracownikServiceImp.save(employeetoEdit);
                        return null;
                    }

                    return null;
                }
            });
            dialog.showAndWait();
        }
    }
    public void deleteEmployee(){
        if(employeesTable.getSelectionModel().getSelectedItem() != null) {
            Pracownik employeetoEdit = (Pracownik) employeesTable.getSelectionModel().getSelectedItem();
            Dialog<Pracownik> dialog = new Dialog<>();
            dialog.setTitle("Usuń pracownika");
            dialog.setResizable(false);
            dialog.setContentText("Pracownik zostanie stale usunięty z bazy danych. Czy na pewno chcesz usunąć pracownika?");
            ButtonType buttonTypeOk = new ButtonType("Usuń", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonCancel = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
            dialog.getDialogPane().getButtonTypes().add(buttonCancel);

            dialog.setResultConverter(new Callback<ButtonType, Pracownik>() {
                @Override
                public Pracownik call(ButtonType b) {

                    if (b == buttonTypeOk) {
                        employeesTable.getItems().remove(employeetoEdit);
                        pracownikServiceImp.delete(employeetoEdit);
                        return null;
                    }

                    return null;
                }
            });

           dialog.showAndWait();
        }
    }
    public void editComment() {
        if (commentsTable.getSelectionModel().getSelectedItem() != null) {
            Komentarz commentToEdit = (Komentarz) commentsTable.getSelectionModel().getSelectedItem();
            Dialog<Pracownik> dialog = new Dialog<>();
            dialog.setTitle("Edytuj komentarz");
            dialog.setResizable(false);
            Label label1 = new Label("Treść: ");
            Label label2 = new Label("Ocena: ");

            TextField text1 = new TextField();
            TextField text2 = new TextField();


            text1.setText(commentToEdit.getKomentarz_tresc());
            text2.setText(Integer.toString(commentToEdit.getKomentarz_ocena()));
            GridPane grid = new GridPane();
            grid.setPrefWidth(600);
            grid.add(label1, 1, 1);
            grid.add(text1, 1, 2);
            grid.add(label2, 2, 1);
            grid.add(text2, 2, 2);
            grid.setMaxSize(800, 200);
            dialog.getDialogPane().setContent(grid);


            ButtonType buttonTypeOk = new ButtonType("Edytuj", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonCancel = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
            dialog.getDialogPane().getButtonTypes().add(buttonCancel);

            dialog.setResultConverter(new Callback<ButtonType, Pracownik>() {
                @Override
                public Pracownik call(ButtonType b) {

                    if (b == buttonTypeOk) {
                       commentsTable.getItems().remove(commentToEdit);
                        commentToEdit.setKomentarz_tresc(text1.getText());
                        commentToEdit.setKomentarz_ocena(Integer.parseInt(text1.getText()));
                        commentsTable.getItems().add(commentToEdit);
                        komentarzServiceImp.save(commentToEdit);
                        return null;
                    }

                    return null;
                }
            });
            dialog.showAndWait();
        }
    }
    public void deleteComment() {
        if(commentsTable.getSelectionModel().getSelectedItem() != null) {
            Komentarz commentToEdit = (Komentarz) commentsTable.getSelectionModel().getSelectedItem();
            Dialog<Komentarz> dialog = new Dialog<>();
            dialog.setTitle("Usuń komentarz");
            dialog.setResizable(false);
            dialog.setContentText("Komentarz zostanie stale usunięty z bazy danych. Czy na pewno chcesz usunąć komentarz?");
            ButtonType buttonTypeOk = new ButtonType("Usuń", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonCancel = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
            dialog.getDialogPane().getButtonTypes().add(buttonCancel);

            dialog.setResultConverter(new Callback<ButtonType, Komentarz>() {
                @Override
                public Komentarz call(ButtonType b) {

                    if (b == buttonTypeOk) {
                        commentsTable.getItems().remove(commentToEdit);
                        komentarzServiceImp.delete(commentToEdit);
                        return null;
                    }

                    return null;
                }
            });

            dialog.showAndWait();
    }
}
}



