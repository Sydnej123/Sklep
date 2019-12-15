package pl.InternetowySklepMuzyczny.sklep.fxml_controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.InternetowySklepMuzyczny.sklep.models.AlbumCart;
import pl.InternetowySklepMuzyczny.sklep.models.Cart;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static pl.InternetowySklepMuzyczny.sklep.fxml_controllers.LoginScreenController.springContext;

@Controller
public class ChartPanelController implements Initializable {
    @FXML
    ScrollPane chartScrollPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(AlbumCart albumCart: Cart.getAlbumsInCart()){
            System.out.println(albumCart.getAlbum().getAlbum_nazwa());
        }

    }
    public void goToFinalizeOrder(){

    }
    public void goBackToMainScreen(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainScreen.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        Scene clientScene = new Scene(fxmlLoader.load());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(clientScene);
    }
}
