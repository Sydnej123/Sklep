package pl.InternetowySklepMuzyczny.sklep.fxml_controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.InternetowySklepMuzyczny.sklep.models.Gatunek_muzyki;
import pl.InternetowySklepMuzyczny.sklep.services.Gatunek_muzykiService;
import pl.InternetowySklepMuzyczny.sklep.services.KlientServiceImp;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class FXMLController {

    @FXML
    TitledPane gatunekPane;
    @FXML
    GridPane gatunekGrid;
    @FXML
    Label wrongPassword;
    @FXML
    Button goForwardButton;
    @FXML
    ImageView image;
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;
    @Autowired
    KlientServiceImp klientService;
    @Autowired
    Gatunek_muzykiService gatunek_muzykiService;

    private List<Gatunek_muzyki> gatunki;
    private List<Button> buttons;

    private KlientServiceImp klientServiceImp;

    public void login() {
        try {

            List<Integer> matchedLogins = klientService.getClientByUsername(loginField.getText());
            BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
            if (!matchedLogins.isEmpty() && pe.matches(passwordField.getText(), klientService.getPasswordById(matchedLogins.get(0)).get(0))) {
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainScreen.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Sklep Internetowy");
                stage.setScene(new Scene(root, 1280, 720));
                stage.show();
                Stage stageToClose = (Stage) goForwardButton.getScene().getWindow();
                stageToClose.close();
                buildDefaultMainScreen();
            } else
                wrongPassword.setText("Brak uzytkownika w bazie.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buildDefaultMainScreen() {
        buttons = new ArrayList<>();
        Button temp;
        gatunki = gatunek_muzykiService.findAllGatunek();
        for (Gatunek_muzyki gatunek_muzyki : gatunki) {
            temp = new Button();
            temp.setText(gatunek_muzyki.getGatunek_nazwa());
            buttons.add(temp);
        }
        gatunekGrid.add(buttons.get(0),0,0);
        for (Button b : buttons) {

        }
    }
}
