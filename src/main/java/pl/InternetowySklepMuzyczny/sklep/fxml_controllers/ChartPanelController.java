package pl.InternetowySklepMuzyczny.sklep.fxml_controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.InternetowySklepMuzyczny.sklep.Session;
import pl.InternetowySklepMuzyczny.sklep.models.*;
import pl.InternetowySklepMuzyczny.sklep.services.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static pl.InternetowySklepMuzyczny.sklep.fxml_controllers.LoginScreenController.springContext;

@Controller
public class ChartPanelController implements Initializable {
    @FXML
    ScrollPane chartScrollPane;
    @FXML
    Label sumValue;
    @FXML
    TextArea commentArea;
    @Autowired
    KlientServiceImp klientServiceImp;
    @Autowired
    AlbumServiceImp albumServiceImp;
    @Autowired
    PracownikServiceImp pracownikServiceImp;
    @Autowired
    ZamowienieServiceImp zamowienieServiceImp;
    @Autowired
    Szczegoly_zamowieniaServiceImp szczegoly_zamowieniaServiceImp;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       showCart();
    }
    public void showCart(){
        GridPane grid = new GridPane();
        ImageView image;
        Image tempImage = null;
        AnchorPane tempAnchorPane = null;
        AnchorPane tempAnchorPanePrice = null;
        Label albumNameLabel = null;
        Label albumName = null;
        Label  albumBandLabel = null;
        Label albumBand = null;
        Label albumGenreLabel = null;
        Label albumGenre = null;
        Label albumPriceLabel = null;
        Label albumPrice = null;
        Label countLabel = null;
        Spinner tempSlider = null;
        Button tempButton = null;

        AnchorPane tempAnchorPaneCount;
        AnchorPane tempAnchorPaneDeleteButton;
        sumValue.setText(String.format("%.2f",Cart.countAndgetValue()) + "zł");
        int i =0;
        if(!Cart.getAlbumsInCart().isEmpty()){
            for(AlbumCart albumCart: Cart.getAlbumsInCart()){
                image = new ImageView();
                try {
                    System.out.println(this.getClass().getClassLoader().getResource("album_covers/"+albumCart.getAlbum().getAlbum_zdjecie_sciezka()));
                    tempImage = new Image(this.getClass().getClassLoader().getResourceAsStream("album_covers/"+albumCart.getAlbum().getAlbum_zdjecie_sciezka()), 200, 200, false, false);
                }catch(Exception e){
                    e.printStackTrace();
                }
                tempAnchorPane = new AnchorPane();
                tempAnchorPanePrice = new AnchorPane();
                tempAnchorPaneCount = new AnchorPane();
                tempAnchorPaneDeleteButton = new AnchorPane();

                albumNameLabel = new Label("Tytuł albumu:");
                albumBandLabel = new Label("Zespół:");
                albumGenreLabel = new Label("Gatunek");
                albumPriceLabel = new Label("Cena za sztukę:");
                albumName = new Label(albumCart.getAlbum().getAlbum_nazwa());
                albumName.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
                albumBand = new Label(albumCart.getAlbum().getZespol().getZespol_nazwa());
                albumBand.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
                albumGenre= new Label(albumCart.getAlbum().getGatunek_muzyki().getGatunek_nazwa());
                albumGenre.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
                albumPrice = new Label(albumCart.getAlbum().getAlbum_cena()+"zł");
                albumPrice.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
                countLabel = new Label("Ilość:");
                tempAnchorPane.getChildren().addAll(albumNameLabel, albumName, albumBandLabel, albumBand, albumGenreLabel, albumGenre);
                AnchorPane.setTopAnchor(albumNameLabel,0.0);
                AnchorPane.setTopAnchor(albumName,20.0);
                AnchorPane.setTopAnchor(albumBandLabel,80.0);
                AnchorPane.setTopAnchor(albumBand,100.0);
                AnchorPane.setTopAnchor(albumGenreLabel,160.0);
                AnchorPane.setTopAnchor(albumGenre,180.0);

                AnchorPane.setLeftAnchor(albumNameLabel, 5.0);
                AnchorPane.setLeftAnchor(albumName, 5.0);
                AnchorPane.setLeftAnchor(albumBandLabel, 5.0);
                AnchorPane.setLeftAnchor(albumBand, 5.0);
                AnchorPane.setLeftAnchor(albumGenreLabel, 5.0);
                AnchorPane.setLeftAnchor(albumGenre, 5.0);

                tempAnchorPanePrice.getChildren().addAll(albumPriceLabel, albumPrice);
                AnchorPane.setLeftAnchor(albumPriceLabel, 20.0);
                AnchorPane.setLeftAnchor(albumPrice, 20.0);
                AnchorPane.setTopAnchor(albumPriceLabel, 0.0);
                AnchorPane.setTopAnchor(albumPrice, 20.0);
                tempButton = new Button("Usuń z koszyka");
                tempButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Cart.getAlbumsInCart().remove(albumCart);
                        showCart();

                    }
                });
                tempSlider = new Spinner<Integer>(1,albumCart.getAlbum().getAlbum_ilosc(), albumCart.getAlbumCount());
                tempSlider.valueProperty().addListener((obs, oldValue, newValue) ->
                        changeAlbumCount(newValue, albumCart)
                );

                tempAnchorPaneCount.getChildren().addAll(countLabel, tempSlider);
                tempAnchorPaneCount.setPrefWidth(600);
                AnchorPane.setTopAnchor(countLabel, 80.0);
                AnchorPane.setTopAnchor(tempSlider, 100.0);
                AnchorPane.setLeftAnchor(countLabel, 500.0);
                AnchorPane.setLeftAnchor(tempSlider, 500.0);
                tempAnchorPaneDeleteButton.getChildren().add(tempButton);
                AnchorPane.setTopAnchor(tempButton, 100.0);
                AnchorPane.setLeftAnchor(tempButton, 10.0);
                image.setImage(tempImage);
                grid.addRow(i, image, tempAnchorPane, tempAnchorPanePrice,tempAnchorPaneCount,tempAnchorPaneDeleteButton);
                i++;
            }


        }
        chartScrollPane.setContent(grid);
    }
    public void changeAlbumCount(Object newn, AlbumCart albumCart){
        albumCart.setAlbumCount( (int) newn);
        sumValue.setText(String.format("%.2f",Cart.countAndgetValue()) + "zł");
    }
    public void goToFinalizeOrder(){
        Klient klient = klientServiceImp.findById(Session.userID);
        if(!Cart.getAlbumsInCart().isEmpty()){
            if(klient.getKlient_imie() != null && klient.getKlient_nazwisko() != null && klient.getKlient_miasto() != null && klient.getKlient_kod_pocztowy() != null && klient.getKlient_nr_domu() != null && klient.getKlient_nr_mieszkania() != null && klient.getKlient_ulica() != null){
                if(Cart.getAlbumsInCart().stream().allMatch(c-> c.getAlbumCount() <= albumServiceImp.findById(c.getAlbum().getAlbum_id()).getAlbum_ilosc())){
                    Zamowienie zamowienie = new Zamowienie();
                    zamowienie.setZamowienie_status(0);
                    zamowienie.setKlient(klient);
                    zamowienie.setZamowienie_wartosc((float)  Cart.countAndgetValue());
                    zamowienie.setZamowienie_komentarz(commentArea.getText());
                    zamowienie.setPracownik(pracownikServiceImp.getPracownikByID(1).get(0));
                    zamowienie.setZamowienie_data(new Date(System.currentTimeMillis()));
                    zamowienieServiceImp.save(zamowienie);
                    Set<Szczegoly_zamowienia> szczegoly_zamowienia = new HashSet<>();
                    Szczegoly_zamowienia temp;
                    for(AlbumCart albumCart: Cart.getAlbumsInCart()){
                        temp = new Szczegoly_zamowienia(new CompositePrimaryKeySzcze_zam(zamowienie.getZamowienie_id(), albumCart.getAlbum().getAlbum_id()));
                        temp.setSzcze_zam_cena_jednostki((double) albumCart.getAlbum().getAlbum_cena());
                        temp.setSzcze_zam_ilosc(albumCart.getAlbumCount());
                        albumCart.getAlbum().setAlbum_ilosc(albumCart.getAlbum().getAlbum_ilosc()-albumCart.getAlbumCount());
                        albumServiceImp.save(albumCart.getAlbum());
                        szczegoly_zamowienia.add(temp);
                        szczegoly_zamowieniaServiceImp.save(temp);
                    }
                    zamowienie.setSzczegoly_zamowienia(szczegoly_zamowienia);
                    zamowienieServiceImp.save(zamowienie);
                    Cart.getAlbumsInCart().clear();
                    commentArea.clear();
                    showCart();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Zamówienie zostało złożone.", ButtonType.OK);
                    alert.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Brak towaru na stanie.", ButtonType.OK);
                    alert.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING, "Przed złożeniem zamówienia należy uzupełnić dane w panelu użytkownika.", ButtonType.OK);
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "Brak towarów w koszyku.", ButtonType.OK);
            alert.showAndWait();

        }

    }
    public void goBackToMainScreen(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainScreen.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        Scene clientScene = new Scene(fxmlLoader.load());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(clientScene);
    }
}
