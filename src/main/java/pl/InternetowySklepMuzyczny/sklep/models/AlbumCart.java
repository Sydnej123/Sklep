package pl.InternetowySklepMuzyczny.sklep.models;

public class AlbumCart {
    private Album album;

    public AlbumCart(Album album, int albumCount) {
        this.album = album;
        this.albumCount = albumCount;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public int getAlbumCount() {
        return albumCount;
    }

    public void setAlbumCount(int albumCount) {
        this.albumCount = albumCount;
    }

    private int albumCount;
}
