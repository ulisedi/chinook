package dao;

import model.Artist;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO {

    public List<Artist> getAllArtists() {

        List<Artist> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM Artist";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Artist a = new Artist();
                a.setArtistId(rs.getInt("ArtistId"));
                a.setName(rs.getString("Name"));

                list.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public int getNextArtistId() {
        int nextId = 1; // por defecto si no hay registros
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT MAX(ArtistId) AS maxId FROM Artist";
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

    public void addArtist(String name) {
        try (Connection con = DBConnection.getConnection()) {
            int id = getNextArtistId();
            String sql = "INSERT INTO Artist(ArtistId, Name) VALUES(?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Artist getArtistById(int id) {

        Artist a = null;

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM Artist WHERE ArtistId=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                a = new Artist();
                a.setArtistId(rs.getInt("ArtistId"));
                a.setName(rs.getString("Name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return a;
    }

    public void updateArtist(int id, String name) {

        try (Connection con = DBConnection.getConnection()) {

            String sql = "UPDATE Artist SET Name=? WHERE ArtistId=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteArtist(int id) {

        try (Connection con = DBConnection.getConnection()) {

            String sql = "DELETE FROM Artist WHERE ArtistId=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}