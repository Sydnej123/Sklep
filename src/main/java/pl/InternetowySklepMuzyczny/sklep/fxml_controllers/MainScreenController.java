package pl.InternetowySklepMuzyczny.sklep.fxml_controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.InternetowySklepMuzyczny.sklep.Session;
import pl.InternetowySklepMuzyczny.sklep.models.*;
import pl.InternetowySklepMuzyczny.sklep.services.AlbumServiceImp;
import pl.InternetowySklepMuzyczny.sklep.services.Gatunek_muzykiServiceImp;
import pl.InternetowySklepMuzyczny.sklep.services.KlientServiceImp;
import pl.InternetowySklepMuzyczny.sklep.services.ZespolServiceImp;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static pl.InternetowySklepMuzyczny.sklep.fxml_controllers.LoginScreenController.springContext;

@Controller
public class MainScreenController  implements Initializable {

    private List<Album> albums;
    private List<Album> filteredAlbums = new ArrayList<>();
    @FXML
    TitledPane gatunekPane;

    @FXML
    TitledPane zespolPane;

    @FXML
    ScrollPane explorerScrollPane;

    @FXML
    Label loggedAsLabel;
    @FXML
    Spinner minimalValue,maximumValue;
    @FXML
    TextField searchTextField;
    @Autowired
    private Gatunek_muzykiServiceImp gatunek_muzykiServiceImp;

    @Autowired
    private KlientServiceImp klientService;

    @Autowired
    private AlbumServiceImp albumServiceImp;

    @Autowired
    private ZespolServiceImp zespolServiceImp;

    private final String paneButtonStyle = "-fx-width: 100px;";
    private List<CheckBox> genreBoxes;
    private List<CheckBox> bandBoxes;
    private List<Album> filteredByBand = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loggedAsLabel.setText("Zalogowano jako: "+klientService.findLoginByID(Session.userID).get(0));
        GridPane genreGridPane = new GridPane();
        GridPane zespolGridPane = new GridPane();
        genreBoxes = new ArrayList<>();
        List<Gatunek_muzyki> k = gatunek_muzykiServiceImp.findAllGatunek();
        int i =0;
        for(Gatunek_muzyki gatunek: k){
            genreBoxes.add(new CheckBox());
            genreBoxes.get(i).setText(gatunek.getGatunek_nazwa()+" ("+albumServiceImp.countGatunek(gatunek.getGatunek_id())+")");
            genreBoxes.get(i).setPrefSize(200,25);
            genreBoxes.get(i).setTextAlignment(TextAlignment.LEFT);
            genreGridPane.addRow(i,genreBoxes.get(i));
            i++;
        }
        i=0;
        ArrayList<Zespol> zespols = zespolServiceImp.findAll();
        bandBoxes = new ArrayList<>();
        for(Zespol zespol: zespols){
            bandBoxes.add(new CheckBox());
            bandBoxes.get(i).setText(zespol.getZespol_nazwa()+" ("+albumServiceImp.countBandAlbums(zespol.getZespol_id())+")");
            bandBoxes.get(i).setPrefSize(200,25);
            bandBoxes.get(i).setTextAlignment(TextAlignment.LEFT);
            zespolGridPane.addRow(i,bandBoxes.get(i));
            i++;
        }
        zespolPane.setContent(zespolGridPane);
        gatunekPane.setContent(genreGridPane);
        albums = albumServiceImp.findAll();
        List<BorderPane> temporaryBorderPane = new ArrayList<>();
        List<AnchorPane> temporaryAnchorPane = new ArrayList<>();
        Label albumNameLabel,albumName,bandLabel,band,priceLabel,price;
        List<Button> addToChartButtons = new ArrayList<>();

        i=0;
        ImageView image;
        Image  tempImage = null;
        GridPane explorerGrid = new GridPane();
        for(Album album : albums){
            temporaryBorderPane.add(new BorderPane());
            image = new ImageView();
            try {
                tempImage = new Image(this.getClass().getClassLoader().getResourceAsStream("album_covers/"+album.getAlbum_zdjecie_sciezka()), 340, 340, false, false);
            }catch(Exception e){
                e.printStackTrace();
            }
            image.setImage(tempImage);
            temporaryBorderPane.get(i).setCenter(image);
            albumName = new Label();
            albumName.setText(album.getAlbum_nazwa());
            band = new Label();
            band.setText(album.getZespol().getZespol_nazwa());

            price = new Label();
            price.setText(Float.toString(album.getAlbum_cena()));

            addToChartButtons.add(new Button());
            addToChartButtons.get(i).setText("Dodaj do koszyka");
            addToChartButtons.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    addToChart(album);
                }
            });
            albumNameLabel = new Label();
            albumNameLabel.setText("Nazwa albumu");
            bandLabel = new Label();
            bandLabel.setText("Wykonawca/zespół");
            priceLabel = new Label();
            priceLabel.setText("Cena");
            temporaryAnchorPane.add(new AnchorPane());
            temporaryAnchorPane.get(i).getChildren().addAll(albumName,albumNameLabel,band,bandLabel,priceLabel,price,addToChartButtons.get(i));
            temporaryAnchorPane.get(i).setPrefSize(350,200);
            AnchorPane.setTopAnchor(albumNameLabel,10.0);
            AnchorPane.setLeftAnchor(albumNameLabel,10.0);
            AnchorPane.setTopAnchor(albumName,10.0);
            AnchorPane.setRightAnchor(albumName,10.0);

            AnchorPane.setTopAnchor(bandLabel,50.0);
            AnchorPane.setLeftAnchor(bandLabel,10.0);
            AnchorPane.setTopAnchor(band, 50.0);
            AnchorPane.setRightAnchor(band, 10.0);

            AnchorPane.setTopAnchor(priceLabel,90.0);
            AnchorPane.setLeftAnchor(priceLabel,10.0);

            AnchorPane.setRightAnchor(price,10.0);
            AnchorPane.setTopAnchor(price,90.0);

            AnchorPane.setRightAnchor(addToChartButtons.get(i),10.0);
            AnchorPane.setTopAnchor(addToChartButtons.get(i),130.0);

            temporaryBorderPane.get(i).setBottom(temporaryAnchorPane.get(i));
            explorerGrid.add(temporaryBorderPane.get(i),i%3,i/3);
            i++;

        }
        explorerScrollPane.setContent(explorerGrid);
        minimalValue.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0,150,0));
        minimalValue.setEditable(true);
        maximumValue.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0,150,100));
        maximumValue.setEditable(true);
        }



    private void addToChart(Album album) {
        if(album.getAlbum_ilosc() == 0){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Brak wybranego towaru.", ButtonType.OK);
            alert.showAndWait();

        }
        else if(Cart.getAlbumsInCart().stream().noneMatch(c-> c.getAlbum() == album)){
            Cart.getAlbumsInCart().add(new AlbumCart(album, 1));
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Dodano do koszyka.", ButtonType.OK);
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "Album już znajduję się w koszyku.", ButtonType.OK);
            alert.showAndWait();
        }

    }

    public void filterAlbums(){
            filteredAlbums.clear();
            filteredByBand.clear();
            for(CheckBox checkBox:genreBoxes){
                if(checkBox.isSelected()){
                    filteredAlbums.addAll(albums.stream().filter(album -> album.getGatunek_muzyki().getGatunek_id() == genreBoxes.indexOf(checkBox)+1).collect(Collectors.toList()));
                }

            }
            if(filteredAlbums.isEmpty()){
                filteredAlbums = albumServiceImp.findAll();
            }
            filteredByBand.addAll(filteredAlbums) ;
            for(CheckBox checkBox: bandBoxes){
                if(!checkBox.isSelected()){
                    filteredAlbums.removeAll(filteredAlbums.stream().filter(album -> album.getZespol().getZespol_id() == bandBoxes.indexOf(checkBox)+1).collect(Collectors.toList()));
                }
            }
            if(filteredAlbums.isEmpty()){
                filteredAlbums.addAll(filteredByBand);
            }


            if(minimalValue.getValue() != null){
                filteredAlbums = filteredAlbums.stream().filter(album -> album.getAlbum_cena() >= (Double) minimalValue.getValue()).collect(Collectors.toList());
            }
            if(maximumValue.getValue() != null){
                filteredAlbums = filteredAlbums.stream().filter(album -> album.getAlbum_cena() <= (Double)maximumValue.getValue()).collect(Collectors.toList());
            }

            repaintExplorer(filteredAlbums);
    }

    public void repaintExplorer(List<Album> albumsToPaint) {

        List<BorderPane> temporaryBorderPane = new ArrayList<>();
        List<AnchorPane> temporaryAnchorPane = new ArrayList<>();
        Label albumNameLabel, albumName, bandLabel, band, priceLabel, price;
        List<Button> addToChartButtons = new ArrayList<>();
        int i = 0;
        ImageView image;
        Image tempImage = null;
        GridPane explorerGrid = new GridPane();
        for (Album album : albumsToPaint) {
            temporaryBorderPane.add(new BorderPane());
            image = new ImageView();
            try {
                tempImage = new Image(this.getClass().getClassLoader().getResourceAsStream("album_covers/"+album.getAlbum_zdjecie_sciezka()), 340, 340, false, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            image.setImage(tempImage);
            temporaryBorderPane.get(i).setCenter(image);
            albumName = new Label();
            albumName.setText(album.getAlbum_nazwa());
            band = new Label();
            band.setText(album.getZespol().getZespol_nazwa());
            price = new Label();
            price.setText(Float.toString(album.getAlbum_cena()));
            addToChartButtons.add(new Button());
            addToChartButtons.get(i).setText("Dodaj do koszyka");
            albumNameLabel = new Label();
            albumNameLabel.setText("Nazwa albumu");
            bandLabel = new Label();
            bandLabel.setText("Wykonawca/zespół");
            priceLabel = new Label();
            priceLabel.setText("Cena");
            temporaryAnchorPane.add(new AnchorPane());
            temporaryAnchorPane.get(i).getChildren().addAll(albumName, albumNameLabel, band, bandLabel, priceLabel, price, addToChartButtons.get(i));
            temporaryAnchorPane.get(i).setPrefSize(350, 200);
            AnchorPane.setTopAnchor(albumNameLabel, 10.0);
            AnchorPane.setLeftAnchor(albumNameLabel, 10.0);
            AnchorPane.setTopAnchor(albumName, 10.0);
            AnchorPane.setRightAnchor(albumName, 10.0);
            AnchorPane.setTopAnchor(bandLabel, 50.0);
            AnchorPane.setLeftAnchor(bandLabel, 10.0);
            AnchorPane.setTopAnchor(band, 50.0);
            AnchorPane.setRightAnchor(band, 10.0);
            AnchorPane.setTopAnchor(priceLabel, 90.0);
            AnchorPane.setLeftAnchor(priceLabel, 10.0);
            AnchorPane.setRightAnchor(price, 10.0);
            AnchorPane.setTopAnchor(price, 90.0);
            AnchorPane.setRightAnchor(addToChartButtons.get(i), 10.0);
            AnchorPane.setTopAnchor(addToChartButtons.get(i), 130.0);
            temporaryBorderPane.get(i).setBottom(temporaryAnchorPane.get(i));
            explorerGrid.add(temporaryBorderPane.get(i), i % 3, i / 3);
            i++;
        }
        explorerScrollPane.setContent(explorerGrid);

    }
    public void searchForAlbum(){
        if(!searchTextField.getText().isEmpty()){
            repaintExplorer(albums.stream().filter(album -> album.getZespol().getZespol_nazwa().toUpperCase().equals(searchTextField.getText().toUpperCase())
                    || album.getGatunek_muzyki().getGatunek_nazwa().toUpperCase().equals(searchTextField.getText().toUpperCase())
                    || album.getAlbum_nazwa().toUpperCase().equals(searchTextField.getText().toUpperCase())).collect(Collectors.toList()));
        }else{
            repaintExplorer(albums);
        }
    }
    public void showAccountDetails(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/user_panel.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        Scene clientScene = new Scene(fxmlLoader.load());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(clientScene);
    }
    public void showChart(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/chart_panel.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        Scene clientScene = new Scene(fxmlLoader.load());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(clientScene);
    }

}
