package dao;

import model.Genre;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {

    public List<Genre> getAllGenre() {

        List<Genre> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM Genre";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Genre g = new Genre();
                g.setGenreId(rs.getInt("GenreId"));
                g.setName(rs.getString("Name"));

                list.add(g);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public int getNextGenreId() {
        int nextId = 1; // por defecto si no hay registros
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT MAX(GenreId) AS maxId FROM Genre";
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

    public void addGenre(String name) {
        try (Connection con = DBConnection.getConnection()) {
            int id = getNextGenreId();
            String sql = "INSERT INTO Genre(GenreId, Name) VALUES(?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Genre getGenreById(int id) {

        Genre g = null;

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM Genre WHERE GenreId=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                g = new Genre();
                g.setGenreId(rs.getInt("GenreId"));
                g.setName(rs.getString("Name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return g;
    }

    public void updateGenre(int id, String name) {

        try (Connection con = DBConnection.getConnection()) {

            String sql = "UPDATE Genre SET Name=? WHERE GenreId=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteGenre(int id) {

        try (Connection con = DBConnection.getConnection()) {

            String sql = "DELETE FROM Genre WHERE GenreId=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}