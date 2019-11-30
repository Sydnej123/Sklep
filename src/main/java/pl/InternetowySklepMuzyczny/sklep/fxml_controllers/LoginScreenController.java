package pl.InternetowySklepMuzyczny.sklep.fxml_controllers;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import pl.InternetowySklepMuzyczny.sklep.Session;
import pl.InternetowySklepMuzyczny.sklep.models.Klient;
import pl.InternetowySklepMuzyczny.sklep.services.KlientServiceImp;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @FXML
    Button registrationButton;
    @Autowired
    private KlientServiceImp klientService;

    private final static  String SHORT_LOGIN_FIELD = "Zbyt krótki login";
    private final static String SHORT_PASSWORD_FIELD = "Zbyt krótkie hasło";
    private final static String PASSWORD_MISMATCH = "Hasła nie są identyczne";
    private final static String INVALID_LOGIN = "Login posiada niedozwolone znaki";
    private final static String LOGIN_EXISTS = "Podany login jest juz zajęty";
    private final static String EMAIL_EXISTS = "Podany e-mail jest już zajęty";
    private final static String ACCEPT_RULES = "Wymagane jest zaakceptowanie regulaminu";
    private final static String INVALID_EMAIL = "Błędny email";
    private Pattern emailPattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    private Pattern loginPattern = Pattern.compile("([a-zA-Z0-9])\\w+");
    private boolean dataValidate;
    private BCryptPasswordEncoder encoder;
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
                Session.userID= matchedLogins.get(0);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainScreen.fxml"));
                fxmlLoader.setControllerFactory(springContext::getBean);
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Sklep Internetowy");
                stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/icons/shopping.png")));
                stage.setScene(new Scene(root, 1280, 720));
                stage.show();

                Stage stageToClose = (Stage) goForwardButton.getScene().getWindow();
                stageToClose.close();
            } else
                wrongPassword.setText("Brak uzytkownika w bazie.");
                wrongPassword.setTextFill(Color.RED);
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
        Matcher matcher = emailPattern.matcher(registrationEmailField.getText());
        dataValidate = true;

        if(registrationLoginField.getText() == null || registrationLoginField.getText().length() < 5){
            registrationErrorLabel.setText(SHORT_LOGIN_FIELD);
            registrationErrorLabel.setVisible(true);
            dataValidate = false;
        }

        if(registrationPasswordField.getText() == null || registrationPasswordField.getText().length() < 5){
            registrationErrorLabel.setText(SHORT_PASSWORD_FIELD);
            registrationErrorLabel.setVisible(true);
            dataValidate = false;
        }
        if(!registrationPasswordField.getText().equals(registrationRepeatPasswordField.getText())){
            registrationErrorLabel.setText(PASSWORD_MISMATCH);
            registrationErrorLabel.setVisible(true);
            dataValidate = false;
        }
        if(!registrationCheckBox.isSelected()){
            registrationErrorLabel.setText(ACCEPT_RULES);
            registrationErrorLabel.setVisible(true);
            dataValidate = false;
        }
        if(!klientService.findbyEmail(registrationEmailField.getText()).isEmpty()){
            registrationErrorLabel.setText(EMAIL_EXISTS);
            registrationErrorLabel.setVisible(true);
            dataValidate = false;
        }

        if(registrationEmailField.getText() == null || !matcher.matches()){
            registrationErrorLabel.setText(INVALID_EMAIL);
            registrationErrorLabel.setVisible(true);
            dataValidate = false;
        }
        if(!klientService.getClientByUsername(registrationLoginField.getText()).isEmpty()){
            registrationErrorLabel.setText(LOGIN_EXISTS);
            registrationErrorLabel.setVisible(true);
            dataValidate = false;
        }
        matcher = loginPattern.matcher(registrationLoginField.getText());
        if(!matcher.matches()){
            registrationErrorLabel.setText(INVALID_LOGIN);
            registrationErrorLabel.setVisible(true);
            dataValidate = false;
        }
        if(dataValidate){
            Klient klient = new Klient();
            klient.setKlient_login(registrationLoginField.getText());
            klient.setKlient_email(registrationEmailField.getText());
            encoder = new BCryptPasswordEncoder();
            klient.setKlient_haslo(encoder.encode(registrationPasswordField.getText()));
            klientService.save(klient);
            tabPane.getSelectionModel().select(logowanieTab);
            loginField.setText(klient.getKlient_login());
            wrongPassword.setTextFill(Color.GREEN);
            wrongPassword.setText("Pomyślnie zarejestrowano!");
        }

    }
    public void exit(){
        Stage stageToClose = (Stage) goForwardButton.getScene().getWindow();
        stageToClose.close();
        springContext.close();
    }
    public void openHelpWindow(){

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

            registrationButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                register();
                }
            });
            Tooltip loginToolTip = new Tooltip();
            loginToolTip.setText("Wpisz swój login");
            loginField.setTooltip(loginToolTip);

            Tooltip passwordToolTip = new Tooltip();
            passwordToolTip.setText("Wpisz hasło");
            passwordField.setTooltip(passwordToolTip);

            Tooltip checkBoxToolTip = new Tooltip();
            checkBoxToolTip.setText("Zaznacz, jeśli chcesz zapamiętać login.");
            rememberBox.setTooltip(checkBoxToolTip);

            Tooltip loginButtonToolTip = new Tooltip();
            loginButtonToolTip.setText("Zaloguj się");
            goForwardButton.setTooltip(loginButtonToolTip);

            Tooltip registerLoginToolTip = new Tooltip();
            registerLoginToolTip.setText("Login powinien składać się z conajmniej 5 znaków alfanumerycznych");
            registrationLoginField.setTooltip(registerLoginToolTip);

            Tooltip registerPasswordToolTip = new Tooltip();
            passwordToolTip.setText("Hasło powinno być nie krótsze niż 5 znaków");
            registrationPasswordField.setTooltip(passwordToolTip);

            Tooltip registerPasswordRepeatToolTip = new Tooltip();
            registerPasswordRepeatToolTip.setText("Powtórz hasło");
            registrationRepeatPasswordField.setTooltip(registerPasswordRepeatToolTip);

            Tooltip registerEmailToolTip = new Tooltip();
            registerEmailToolTip.setText("Wpisz adres e-mail");
            registrationEmailField.setTooltip(registerEmailToolTip);

            Tooltip registerCheckBoxToolTip = new Tooltip();
            registerCheckBoxToolTip.setText("Do rejestracji konieczne jest zaakceptowanie regulaminu");
            registrationCheckBox.setTooltip(registerCheckBoxToolTip);

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
