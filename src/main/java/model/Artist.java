package model;

/**
 * Artist - Modelo POJO (Plain Old Java Object)
 * Representa la entidad Artist de la base de datos
 * Almacena información básica de un artista: ID y nombre
 */
public class Artist {

    
    private int artistId;      // ID único del artista (clave primaria)
    private String name;       // Nombre del artista

    
    public Artist() {
    }

    
    public Artist(int artistId, String name) {
        this.artistId = artistId;
        this.name = name;
    }

    
    public int getArtistId() {
        return artistId;
    }

    
    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

   
    public String getName() {
        return name;
    }

  
    public void setName(String name) {
        this.name = name;
    }
}