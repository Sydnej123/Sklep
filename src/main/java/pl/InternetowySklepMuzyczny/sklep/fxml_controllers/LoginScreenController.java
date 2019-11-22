package pl.InternetowySklepMuzyczny.sklep.fxml_controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import pl.InternetowySklepMuzyczny.sklep.services.KlientServiceImp;

import java.util.List;

@Controller
public class LoginScreenController {

    @FXML
    Label wrongPassword;
    @FXML
    Button goForwardButton;
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;
    @Autowired
    private KlientServiceImp klientService;


    public static ConfigurableApplicationContext springContext;
    public void login() {
        try {

            List<Integer> matchedLogins = klientService.getClientByUsername(loginField.getText());
            BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
            if (!matchedLogins.isEmpty() && pe.matches(passwordField.getText(), klientService.getPasswordById(matchedLogins.get(0)).get(0))) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainScreen.fxml"));
                fxmlLoader.setControllerFactory(springContext::getBean);
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Sklep Internetowy");
                stage.setScene(new Scene(root, 1280, 720));
                stage.show();
                Stage stageToClose = (Stage) goForwardButton.getScene().getWindow();
                stageToClose.close();

            } else
                wrongPassword.setText("Brak uzytkownika w bazie.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
