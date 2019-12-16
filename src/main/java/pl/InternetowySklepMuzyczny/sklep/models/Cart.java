package pl.InternetowySklepMuzyczny.sklep.models;

import pl.InternetowySklepMuzyczny.sklep.models.Album;

import java.util.ArrayList;
import java.util.List;
public class Cart {
    public static List<AlbumCart> getAlbumsInCart() {
        return albumsInCart;
    }

    public static void setAlbumsInCart(List<AlbumCart> albumsInCart) {
        Cart.albumsInCart = albumsInCart;
    }

    private static List<AlbumCart> albumsInCart = new ArrayList<>();

    public static double countAndgetValue() {
        double valueToShow = 0;
        for(AlbumCart albumCart: albumsInCart){
            valueToShow += albumCart.getAlbum().getAlbum_cena() * albumCart.getAlbumCount();
    }
        return valueToShow;
}}
