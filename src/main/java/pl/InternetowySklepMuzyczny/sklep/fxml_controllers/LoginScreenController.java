package pl.InternetowySklepMuzyczny.sklep.fxml_controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import pl.InternetowySklepMuzyczny.sklep.services.KlientServiceImp;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class LoginScreenController implements Initializable {
    @FXML
    CheckBox rememberBox;
    @FXML
    TabPane tabPane;
    @FXML
    Tab logowanieTab;
    @FXML
    Tab rejestracjaTab;
    @FXML
    Label wrongPassword;
    @FXML
    Button goForwardButton;
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;
    @FXML
    VBox vBoxScene;
    @FXML
    TextField registrationLoginField;
    @FXML
    PasswordField registrationPasswordField;
    @FXML
    PasswordField registrationRepeatPasswordField;
    @FXML
    TextField registrationEmailField;
    @FXML
    CheckBox registrationCheckBox;
    @FXML
    Label registrationErrorLabel;

    @Autowired
    private KlientServiceImp klientService;

    private final static  String SHORT_LOGIN_FIELD = "Zbyt krótki login";
    private final static String SHORT_PASSWORD_FIELD = "Zbyt krótkie hasło";
    private final static String PASSWORD_MISMATCH = "Hasła nie są identyczne";
    private final static String INVALID_LOGIN = "Login posiada niedozwolone znaki";
    private final static String LOGIN_EXISTS = "Podany login jest juz zajęty";
    private final static String EMAIL_EXISTS = "Podany e-mail jest już zajęty";
    private final static String ACCEPT_RULES = "Wymagane jest zaakceptowanie regulaminu";
    public static ConfigurableApplicationContext springContext;



    public void login() {
        try {

            List<Integer> matchedLogins = klientService.getClientByUsername(loginField.getText());
            BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
            if (!matchedLogins.isEmpty() && pe.matches(passwordField.getText(), klientService.getPasswordById(matchedLogins.get(0)).get(0))) {
                if(rememberBox.isSelected()){
                    PrintWriter writer = new PrintWriter("login.txt");
                    writer.println(loginField.getText());
                    writer.close();
                }else{
                    PrintWriter writer = new PrintWriter(new FileWriter("login.txt",false) ,false);
                    writer.flush();
                    writer.close();
                }

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

    public void handleOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            if(logowanieTab.isSelected()){
                login();
            }else
                register();
        }


    }

    private void register() {
    if(registrationLoginField.getText() == null || registrationLoginField.getText().length() < 5){
        registrationErrorLabel.setText(SHORT_LOGIN_FIELD);
        registrationErrorLabel.setVisible(true);
    }
    if(registrationPasswordField.getText() == null || registrationPasswordField.getText().length() < 5){
        registrationErrorLabel.setText(SHORT_PASSWORD_FIELD);
        registrationErrorLabel.setVisible(true);
    }
    if(!registrationPasswordField.getText().equals(registrationRepeatPasswordField.getText())){

    }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("login.txt"));
            String temp = br.readLine();
            loginField.setText(temp);
            if(!(temp == null)){
                rememberBox.setSelected(true);
            }

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }
}
