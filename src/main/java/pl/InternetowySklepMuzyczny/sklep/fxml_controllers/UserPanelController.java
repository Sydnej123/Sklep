package pl.InternetowySklepMuzyczny.sklep.fxml_controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.InternetowySklepMuzyczny.sklep.Session;
import pl.InternetowySklepMuzyczny.sklep.models.Szczegoly_zamowienia;
import pl.InternetowySklepMuzyczny.sklep.models.Zamowienie;
import pl.InternetowySklepMuzyczny.sklep.services.AlbumServiceImp;
import pl.InternetowySklepMuzyczny.sklep.services.Szczegoly_zamowieniaServiceImp;
import pl.InternetowySklepMuzyczny.sklep.services.ZamowienieServiceImp;

import javax.persistence.criteria.Root;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
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
    private boolean firstTime = true;

    public void backToMainScreen(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainScreen.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        Scene clientScene = new Scene(fxmlLoader.load());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(clientScene);

    }
    public void showPassword(){
        //passwordField.setText() // setPassword;

    }
    public void editUserData(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (ordersTable.getColumns().isEmpty()) {
            TableColumn<Zamowienie, String> idColumn = new TableColumn<>("Id");
            TableColumn<Zamowienie, String> valueColumn = new TableColumn<>("Wartość");
            TableColumn<Zamowienie, String> commentColumn = new TableColumn<>("Komentarz");
            TableColumn<Zamowienie, String> dateColumn = new TableColumn<>("Data");
            TableColumn<Zamowienie, String> statusColumn = new TableColumn<>("Status");

            idColumn.setCellValueFactory(new PropertyValueFactory("zamowienie_id"));
            valueColumn.setCellValueFactory(new PropertyValueFactory("zamowienie_wartosc"));
            commentColumn.setCellValueFactory(new PropertyValueFactory("zamowienie_komentarz"));
            statusColumn.setCellValueFactory(new PropertyValueFactory("zamowienie_status"));
            dateColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getZamowienie_data().toString())));
            ordersTable.getColumns().addAll(idColumn, valueColumn, commentColumn, dateColumn, statusColumn);
        }
        ordersTable.getItems().clear();
        ordersTable.getItems().addAll(zamowienieServiceImp.getOrderByClientId(Session.userID));
        if (firstTime) {
            ordersTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    Zamowienie temporary = (Zamowienie) newSelection;
                    orderDetailsFill(temporary.getZamowienie_id());
                }
            });
        }
        firstTime = false;
    }
    public void orderDetailsFill(Integer orderID) {
        if (orderDetailsTable.getColumns().isEmpty()) {
            TableColumn<Szczegoly_zamowienia, String> nameColumn = new TableColumn<>("Nazwa albumu");
            TableColumn<Szczegoly_zamowienia, String> countColumn = new TableColumn<>("Ilość"); // dodanie ceny?
            orderDetailsTable.getColumns().addAll(nameColumn, countColumn);
            nameColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(albumServiceImp.findById(c.getValue().getCompositePrimaryKeySzcze_zam().getAlbum_id()).getAlbum_nazwa())));
            countColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getSzcze_zam_ilosc())));
        }
        orderDetailsTable.getItems().clear();
        orderDetailsTable.getItems().addAll(szczegoly_zamowieniaServiceImp.findByZamowienieId(orderID));
    }
}
