package dao;

import model.Track;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrackDAO {

    public List<Track> getAllTracks() {

        List<Track> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT t.TrackId, t.Name, t.AlbumId, a.Title as AlbumTitle, " +
                    "ar.Name as ArtistName, g.Name as GenreName, t.Milliseconds, t.Bytes, t.UnitPrice " +
                    "FROM Track t " +
                    "LEFT JOIN Album a ON t.AlbumId = a.AlbumId " +
                    "LEFT JOIN Artist ar ON a.ArtistId = ar.ArtistId " +
                    "LEFT JOIN Genre g ON t.GenreId = g.GenreId " +
                    "ORDER BY t.TrackId";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Track t = new Track();
                t.setTrackId(rs.getInt("TrackId"));
                t.setName(rs.getString("Name"));
                t.setAlbumId(rs.getInt("AlbumId"));
                String albumTitle = rs.getString("AlbumTitle");
                t.setAlbumTitle(albumTitle != null ? albumTitle : "Sin álbum");
                String artistName = rs.getString("ArtistName");
                t.setArtistName(artistName != null ? artistName : "Sin artista");
                String genreName = rs.getString("GenreName");
                t.setGenreName(genreName != null ? genreName : "Sin género");
                t.setMilliseconds(rs.getLong("Milliseconds"));
                t.setBytes(rs.getLong("Bytes"));
                t.setUnitPrice(rs.getDouble("UnitPrice"));
                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Track getTrackById(int id) {

        Track t = null;

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT t.TrackId, t.Name, t.AlbumId, a.Title as AlbumTitle, " +
                    "ar.Name as ArtistName, g.Name as GenreName, t.Milliseconds, t.Bytes, t.UnitPrice " +
                    "FROM Track t " +
                    "LEFT JOIN Album a ON t.AlbumId = a.AlbumId " +
                    "LEFT JOIN Artist ar ON a.ArtistId = ar.ArtistId " +
                    "LEFT JOIN Genre g ON t.GenreId = g.GenreId " +
                    "WHERE t.TrackId = ?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                t = new Track();
                t.setTrackId(rs.getInt("TrackId"));
                t.setName(rs.getString("Name"));
                t.setAlbumId(rs.getInt("AlbumId"));
                String albumTitle = rs.getString("AlbumTitle");
                t.setAlbumTitle(albumTitle != null ? albumTitle : "Sin álbum");
                String artistName = rs.getString("ArtistName");
                t.setArtistName(artistName != null ? artistName : "Sin artista");
                String genreName = rs.getString("GenreName");
                t.setGenreName(genreName != null ? genreName : "Sin género");
                t.setMilliseconds(rs.getLong("Milliseconds"));
                t.setBytes(rs.getLong("Bytes"));
                t.setUnitPrice(rs.getDouble("UnitPrice"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }

    public int getNextTrackId() {
        int nextId = 1;
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT MAX(TrackId) AS maxId FROM Track";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nextId = rs.getInt("maxId") + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nextId;
    }

    public void addTrack(String name, int albumId, int genreId, long milliseconds, long bytes, double unitPrice) {
        try (Connection con = DBConnection.getConnection()) {
            int id = getNextTrackId();
            String sql = "INSERT INTO Track(TrackId, Name, AlbumId, GenreId, Milliseconds, Bytes, UnitPrice) VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, albumId);
            ps.setInt(4, genreId);
            ps.setLong(5, milliseconds);
            ps.setLong(6, bytes);
            ps.setDouble(7, unitPrice);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTrack(int trackId, String name, int albumId, int genreId, long milliseconds, long bytes, double unitPrice) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "UPDATE Track SET Name=?, AlbumId=?, GenreId=?, Milliseconds=?, Bytes=?, UnitPrice=? WHERE TrackId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, albumId);
            ps.setInt(3, genreId);
            ps.setLong(4, milliseconds);
            ps.setLong(5, bytes);
            ps.setDouble(6, unitPrice);
            ps.setInt(7, trackId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTrack(int id) {
        try (Connection con = DBConnection.getConnection()) {
            // Primero eliminar de la tabla PlaylistTrack
            String sqlDelete1 = "DELETE FROM PlaylistTrack WHERE TrackId = ?";
            PreparedStatement ps1 = con.prepareStatement(sqlDelete1);
            ps1.setInt(1, id);
            ps1.executeUpdate();

            // Luego eliminar el track
            String sqlDelete2 = "DELETE FROM Track WHERE TrackId = ?";
            PreparedStatement ps2 = con.prepareStatement(sqlDelete2);
            ps2.setInt(1, id);
            ps2.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Track> getTracksByArtist(int artistId) {
        List<Track> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT t.TrackId, t.Name, t.AlbumId, a.Title as AlbumTitle, " +
                    "ar.Name as ArtistName, g.Name as GenreName, t.Milliseconds, t.Bytes, t.UnitPrice " +
                    "FROM Track t " +
                    "LEFT JOIN Album a ON t.AlbumId = a.AlbumId " +
                    "LEFT JOIN Artist ar ON a.ArtistId = ar.ArtistId " +
                    "LEFT JOIN Genre g ON t.GenreId = g.GenreId " +
                    "WHERE ar.ArtistId = ? " +
                    "ORDER BY t.TrackId";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, artistId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Track t = new Track();
                t.setTrackId(rs.getInt("TrackId"));
                t.setName(rs.getString("Name"));
                t.setAlbumId(rs.getInt("AlbumId"));
                String albumTitle = rs.getString("AlbumTitle");
                t.setAlbumTitle(albumTitle != null ? albumTitle : "Sin álbum");
                String artistName = rs.getString("ArtistName");
                t.setArtistName(artistName != null ? artistName : "Sin artista");
                String genreName = rs.getString("GenreName");
                t.setGenreName(genreName != null ? genreName : "Sin género");
                t.setMilliseconds(rs.getLong("Milliseconds"));
                t.setBytes(rs.getLong("Bytes"));
                t.setUnitPrice(rs.getDouble("UnitPrice"));
                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Track> getTracksByGenre(int genreId) {
        List<Track> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT t.TrackId, t.Name, t.AlbumId, a.Title as AlbumTitle, " +
                    "ar.Name as ArtistName, g.Name as GenreName, t.Milliseconds, t.Bytes, t.UnitPrice " +
                    "FROM Track t " +
                    "LEFT JOIN Album a ON t.AlbumId = a.AlbumId " +
                    "LEFT JOIN Artist ar ON a.ArtistId = ar.ArtistId " +
                    "LEFT JOIN Genre g ON t.GenreId = g.GenreId " +
                    "WHERE t.GenreId = ? " +
                    "ORDER BY t.TrackId";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, genreId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Track t = new Track();
                t.setTrackId(rs.getInt("TrackId"));
                t.setName(rs.getString("Name"));
                t.setAlbumId(rs.getInt("AlbumId"));
                String albumTitle = rs.getString("AlbumTitle");
                t.setAlbumTitle(albumTitle != null ? albumTitle : "Sin álbum");
                String artistName = rs.getString("ArtistName");
                t.setArtistName(artistName != null ? artistName : "Sin artista");
                String genreName = rs.getString("GenreName");
                t.setGenreName(genreName != null ? genreName : "Sin género");
                t.setMilliseconds(rs.getLong("Milliseconds"));
                t.setBytes(rs.getLong("Bytes"));
                t.setUnitPrice(rs.getDouble("UnitPrice"));
                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Track> getTracksByAlbum(int albumId) {
        List<Track> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT t.TrackId, t.Name, t.AlbumId, a.Title as AlbumTitle, " +
                    "ar.Name as ArtistName, g.Name as GenreName, t.Milliseconds, t.Bytes, t.UnitPrice " +
                    "FROM Track t " +
                    "LEFT JOIN Album a ON t.AlbumId = a.AlbumId " +
                    "LEFT JOIN Artist ar ON a.ArtistId = ar.ArtistId " +
                    "LEFT JOIN Genre g ON t.GenreId = g.GenreId " +
                    "WHERE t.AlbumId = ? " +
                    "ORDER BY t.TrackId";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, albumId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Track t = new Track();
                t.setTrackId(rs.getInt("TrackId"));
                t.setName(rs.getString("Name"));
                t.setAlbumId(rs.getInt("AlbumId"));
                String albumTitle = rs.getString("AlbumTitle");
                t.setAlbumTitle(albumTitle != null ? albumTitle : "Sin álbum");
                String artistName = rs.getString("ArtistName");
                t.setArtistName(artistName != null ? artistName : "Sin artista");
                String genreName = rs.getString("GenreName");
                t.setGenreName(genreName != null ? genreName : "Sin género");
                t.setMilliseconds(rs.getLong("Milliseconds"));
                t.setBytes(rs.getLong("Bytes"));
                t.setUnitPrice(rs.getDouble("UnitPrice"));
                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
