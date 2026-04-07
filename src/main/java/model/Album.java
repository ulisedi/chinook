package model;

public class Album {

    private int albumId;
    private String title;
    private int artistId;
    private String artistName;

    public Album() {
    }

    public Album(int albumId, String title, int artistId) {
        this.albumId = albumId;
        this.title = title;
        this.artistId = artistId;
    }

    public Album(int albumId, String title, int artistId, String artistName) {
        this.albumId = albumId;
        this.title = title;
        this.artistId = artistId;
        this.artistName = artistName;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
     
     