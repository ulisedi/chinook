package model;

public class Track {

    private int trackId;
    private String name;
    private int albumId;
    private String albumTitle;
    private int artistId;
    private String artistName;
    private int genreId;
    private String genreName;
    private long milliseconds;
    private long bytes;
    private double unitPrice;

    public Track() {
    }

    public Track(int trackId, String name, int albumId) {
        this.trackId = trackId;
        this.name = name;
        this.albumId = albumId;
    }

    public Track(int trackId, String name, int albumId, String albumTitle, String artistName) {
        this.trackId = trackId;
        this.name = name;
        this.albumId = albumId;
        this.albumTitle = albumTitle;
        this.artistName = artistName;
    }

    // Getters y Setters
    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
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

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public long getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public long getBytes() {
        return bytes;
    }

    public void setBytes(long bytes) {
        this.bytes = bytes;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
