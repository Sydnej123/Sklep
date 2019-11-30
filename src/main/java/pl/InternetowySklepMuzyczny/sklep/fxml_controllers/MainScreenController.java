package pl.InternetowySklepMuzyczny.sklep.fxml_controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.InternetowySklepMuzyczny.sklep.Session;
import pl.InternetowySklepMuzyczny.sklep.models.Album;
import pl.InternetowySklepMuzyczny.sklep.models.Gatunek_muzyki;
import pl.InternetowySklepMuzyczny.sklep.services.AlbumServiceImp;
import pl.InternetowySklepMuzyczny.sklep.services.Gatunek_muzykiServiceImp;
import pl.InternetowySklepMuzyczny.sklep.services.KlientServiceImp;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class MainScreenController  implements Initializable {

    private List<Album> albums;
    private List<Album> filteredAlbums = new ArrayList<>();
    @FXML
    TitledPane gatunekPane;

    @FXML
    ScrollPane explorerScrollPane;

    @FXML
    Label loggedAsLabel;
    @Autowired
    private Gatunek_muzykiServiceImp gatunek_muzykiServiceImp;

    @Autowired
    private KlientServiceImp klientService;

    @Autowired
    private AlbumServiceImp albumServiceImp;

    private final String paneButtonStyle = "-fx-width: 100px;";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loggedAsLabel.setText("Zalogowano jako: "+klientService.findLoginByID(Session.userID).get(0));
        GridPane gp = new GridPane();
        List<CheckBox> temporaryBoxes = new ArrayList<>();
        List<Gatunek_muzyki> k = gatunek_muzykiServiceImp.findAllGatunek();
        int i =0;
        for(Gatunek_muzyki gatunek: k){
            temporaryBoxes.add(new CheckBox());
            temporaryBoxes.get(i).setText(gatunek.getGatunek_nazwa()+" ("+albumServiceImp.countGatunek(gatunek.getGatunek_id())+")");
            temporaryBoxes.get(i).setPrefSize(200,25);
            temporaryBoxes.get(i).setTextAlignment(TextAlignment.LEFT);
            gp.addRow(i,temporaryBoxes.get(i));
            i++;
        }
        gatunekPane.setContent(gp);
        albums = albumServiceImp.findAll();
        List<BorderPane> temporaryBorderPane = new ArrayList<>();
        List<AnchorPane> temporaryAnchorPane = new ArrayList<>();
        Label albumNameLabel,albumName,bandLabel,band,priceLabel,price;
        List<Button> addToChartButtons = new ArrayList<>();

        i=0;
        ImageView image;
        Image  tempImage =new Image("Genesis83.jpg");
        GridPane explorerGrid = new GridPane();
        for(Album album : albums){
            temporaryBorderPane.add(new BorderPane());
            image = new ImageView();
            try {
                tempImage = new Image(album.getAlbum_zdjecie_sciezka(), 340, 340, false, false);
            }catch(Exception e){
                System.out.println(album.getAlbum_zdjecie_sciezka());
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
        for(CheckBox box: temporaryBoxes){
            box.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(box.isSelected()){
                        filterByGenre(temporaryBoxes.indexOf(box));
                        repaintExplorer();
                    }else
                        removeGenreFilter();
                }
            });
        }


    }

    private void removeGenreFilter() {
    }

    public void filterByGenre(Integer genreID){
    filteredAlbums.addAll(albumServiceImp.findByGenre(genreID));
    }

    public void repaintExplorer() {

        List<BorderPane> temporaryBorderPane = new ArrayList<>();
        List<AnchorPane> temporaryAnchorPane = new ArrayList<>();
        Label albumNameLabel, albumName, bandLabel, band, priceLabel, price;
        List<Button> addToChartButtons = new ArrayList<>();
        int i = 0;
        ImageView image;
        Image tempImage = new Image("Genesis83.jpg");
        GridPane explorerGrid = new GridPane();
        for (Album album : filteredAlbums) {
            temporaryBorderPane.add(new BorderPane());
            image = new ImageView();
            try {
                tempImage = new Image(album.getAlbum_zdjecie_sciezka(), 340, 340, false, false);
            } catch (Exception e) {
                System.out.println(album.getAlbum_zdjecie_sciezka());
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

    }}
