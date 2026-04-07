package dao;

import model.Album;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {

    public List<Album> getAllAlbum() {

        List<Album> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT a.AlbumId, a.Title, a.ArtistId, ar.Name FROM Album a " +
                        "LEFT JOIN Artist ar ON a.ArtistId = ar.ArtistId";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Album a = new Album();
                a.setAlbumId(rs.getInt("AlbumId"));
                a.setTitle(rs.getString("Title"));
                a.setArtistId(rs.getInt("ArtistId"));
                String artistName = rs.getString("Name");
                a.setArtistName(artistName != null ? artistName : "Sin artista");
                list.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public int getNextAlbumId() {
        int nextId = 1; // por defecto si no hay registros
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT MAX(AlbumId) AS maxId FROM Album";
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

    public void addAlbum(String title, int artistId) {
        try (Connection con = DBConnection.getConnection()) {
            int id = getNextAlbumId();
            String sql = "INSERT INTO Album(AlbumId, Title, ArtistId) VALUES(?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, title);
            ps.setInt(3, artistId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Album getAlbumById(int id) {

        Album a = null;

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM Album WHERE AlbumId=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                a = new Album();
                a.setAlbumId(rs.getInt("AlbumId"));
                a.setTitle(rs.getString("Title"));
                a.setArtistId(rs.getInt("ArtistId"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return a;
    }

    public void updateAlbum(int id, String name) {

        try (Connection con = DBConnection.getConnection()) {

            String sql = "UPDATE Album SET Title=? WHERE AlbumId=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAlbum(int id) {

        try (Connection con = DBConnection.getConnection()) {

            String sql = "DELETE FROM Album WHERE AlbumId=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}