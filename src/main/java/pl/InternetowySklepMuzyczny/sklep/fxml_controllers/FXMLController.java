package pl.InternetowySklepMuzyczny.sklep.fxml_controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import pl.InternetowySklepMuzyczny.sklep.services.KlientServiceImp;

@org.springframework.stereotype.Controller
public class FXMLController {

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

    private KlientServiceImp klientServiceImp;
    public void login(){
        try{
            System.out.println(klientService.getClientByUsername(loginField.getText()));
            System.out.println("Wciskam");
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainScreen.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Sklep Internetowy");
            stage.setScene(new Scene(root, 1280, 720));
            stage.show();
            Stage stageToClose = (Stage) goForwardButton.getScene().getWindow();
            stageToClose.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
