package pl.InternetowySklepMuzyczny.sklep.fxml_controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import pl.InternetowySklepMuzyczny.sklep.Session;
import pl.InternetowySklepMuzyczny.sklep.StatusZamowienia;
import pl.InternetowySklepMuzyczny.sklep.models.*;
import pl.InternetowySklepMuzyczny.sklep.services.AlbumServiceImp;
import pl.InternetowySklepMuzyczny.sklep.services.KlientServiceImp;
import pl.InternetowySklepMuzyczny.sklep.services.Szczegoly_zamowieniaServiceImp;
import pl.InternetowySklepMuzyczny.sklep.services.ZamowienieServiceImp;

import javax.persistence.criteria.Root;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static pl.InternetowySklepMuzyczny.sklep.fxml_controllers.LoginScreenController.springContext;

@Controller
public class UserPanelController implements Initializable {
    @FXML
    TextField loginField, passwordField, emailField, nameField, secondNameField, cityField, streetField, houseNumberField, flatNumberField, zipCodeField;
    @FXML
    TableView ordersTable, orderDetailsTable;
    @Autowired
    ZamowienieServiceImp zamowienieServiceImp;
    @Autowired
    AlbumServiceImp albumServiceImp;
    @Autowired
    Szczegoly_zamowieniaServiceImp szczegoly_zamowieniaServiceImp;
    @Autowired
    KlientServiceImp klientServiceImp;
    private boolean firstTime = true;
    private Klient klient;

    public void backToMainScreen(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainScreen.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        Scene clientScene = new Scene(fxmlLoader.load());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(clientScene);

    }
    public void editPassword(){
        javafx.scene.control.Dialog<Klient> dialog = new Dialog<>();
        dialog.setTitle("Edytuj hasło");
        dialog.setResizable(false);
        Label label1 = new Label("Stare hasło: ");
        Label label2 = new Label("Nowe hasło: ");
        Label label3 = new Label("Powtórz nowe hasło: ");


        PasswordField pf1 = new PasswordField();
        PasswordField pf2 = new PasswordField();
        PasswordField pf3 = new PasswordField();
        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(pf1, 1, 2);
        grid.add(label2, 1, 3);
        grid.add(pf2, 1, 4);
        grid.add(label3, 1, 5);
        grid.add(pf3, 1, 6);
        dialog.getDialogPane().setContent(grid);
        ButtonType buttonTypeOk = new ButtonType("Zmień", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonCancel = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        dialog.getDialogPane().getButtonTypes().add(buttonCancel);

        dialog.setResultConverter(new Callback<ButtonType, Klient>() {
            @Override
            public Klient call(ButtonType b) {

                if (b == buttonTypeOk) {
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    if(encoder.matches(pf1.getText(),klient.getKlient_haslo()) && pf2.getText().equals(pf3.getText())){
                        klient.setKlient_haslo(encoder.encode(pf2.getText()));
                        klientServiceImp.save(klient);
                    }
                    return null;
                }

                return null;
            }
        });
        dialog.showAndWait();

    }
    public void editUserData(){
        klient.setKlient_imie(nameField.getText());
        klient.setKlient_nazwisko(secondNameField.getText());
        klient.setKlient_miasto(cityField.getText());
        klient.setKlient_ulica(streetField.getText());
        klient.setKlient_nr_domu(Integer.parseInt(houseNumberField.getText()));
        klient.setKlient_nr_mieszkania(Integer.parseInt(flatNumberField.getText()));
        klient.setKlient_kod_pocztowy(zipCodeField.getText());
        klientServiceImp.save(klient);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        klient = klientServiceImp.findById(Session.userID);
        if (ordersTable.getColumns().isEmpty()) {
            TableColumn<Zamowienie, String> idColumn = new TableColumn<>("Id");
            TableColumn<Zamowienie, String> valueColumn = new TableColumn<>("Wartość");
            TableColumn<Zamowienie, String> commentColumn = new TableColumn<>("Komentarz");
            TableColumn<Zamowienie, String> dateColumn = new TableColumn<>("Data");
            TableColumn<Zamowienie, String> statusColumn = new TableColumn<>("Status");

            idColumn.setCellValueFactory(new PropertyValueFactory("zamowienie_id"));
            valueColumn.setCellValueFactory(new PropertyValueFactory("zamowienie_wartosc"));
            commentColumn.setCellValueFactory(new PropertyValueFactory("zamowienie_komentarz"));
            statusColumn.setCellValueFactory(c-> new ReadOnlyStringWrapper(String.valueOf(StatusZamowienia.getStatusById(c.getValue().getZamowienie_status()))));
            dateColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getZamowienie_data().toString())));
            ordersTable.getColumns().addAll(idColumn, valueColumn, commentColumn, dateColumn, statusColumn);
        }
        ordersTable.getItems().clear();
        ordersTable.getItems().addAll(zamowienieServiceImp.getOrderByClientId(Session.userID));
            ordersTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    Zamowienie temporary = (Zamowienie) newSelection;
                    orderDetailsFill(temporary.getZamowienie_id());
                }
            });

        if(klient.getKlient_imie() == null){
            nameField.setEditable(true);
            secondNameField.setEditable(true);
        }
        loginField.setText(klient.getKlient_login());
        passwordField.setText("*********");
        emailField.setText(klient.getKlient_email());
        nameField.setText(klient.getKlient_imie());
        secondNameField.setText(klient.getKlient_nazwisko());
        cityField.setText(klient.getKlient_miasto());
        streetField.setText(klient.getKlient_ulica());
        houseNumberField.setText(String.valueOf(klient.getKlient_nr_domu()));
        flatNumberField.setText(String.valueOf(klient.getKlient_nr_mieszkania()));
        zipCodeField.setText(klient.getKlient_kod_pocztowy());
    }
    public void orderDetailsFill(Integer orderID) {
        if (orderDetailsTable.getColumns().isEmpty()) {
            TableColumn<Szczegoly_zamowienia, String> nameColumn = new TableColumn<>("Nazwa albumu");
            TableColumn<Szczegoly_zamowienia, String> countColumn = new TableColumn<>("Ilość");
            TableColumn<Szczegoly_zamowienia, String> valueColumn = new TableColumn<>("Cena jednostki");
            orderDetailsTable.getColumns().addAll(nameColumn, countColumn, valueColumn);
            nameColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(albumServiceImp.findById(c.getValue().getCompositePrimaryKeySzcze_zam().getAlbum_id()).getAlbum_nazwa())));
            countColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getSzcze_zam_ilosc())));
            valueColumn.setCellValueFactory(new PropertyValueFactory("szcze_zam_cena_jednostki"));
        }
        orderDetailsTable.getItems().clear();
        orderDetailsTable.getItems().addAll(szczegoly_zamowieniaServiceImp.findByZamowienieId(orderID));
    }
}
