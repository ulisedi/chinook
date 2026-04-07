package dao;

import model.Playlist;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {

    public List<Playlist> getAllPlaylists() {

        List<Playlist> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM Playlist";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Playlist pl = new Playlist();
                pl.setPlaylistId(rs.getInt("PlaylistId"));
                pl.setName(rs.getString("Name"));

                list.add(pl);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public int getNextPlaylistId() {
        int nextId = 1; // por defecto si no hay registros
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT MAX(PlaylistId) AS maxId FROM Playlist";
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

    public void addPlaylist(String name) {
        try (Connection con = DBConnection.getConnection()) {
            int id = getNextPlaylistId();
            String sql = "INSERT INTO Playlist(PlaylistId, Name) VALUES(?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Playlist getPlaylistById(int id) {

        Playlist a = null;

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM Playlist WHERE PlaylistId=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                a = new Playlist();
                a.setPlaylistId(rs.getInt("PlaylistId"));
                a.setName(rs.getString("Name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return a;
    }

    public void updatePlaylist(int id, String name) {

        try (Connection con = DBConnection.getConnection()) {

            String sql = "UPDATE Playlist SET Name=? WHERE PlaylistId=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePlaylist(int id) {

        try (Connection con = DBConnection.getConnection()) {

            String sql = "DELETE FROM Playlist WHERE PlaylistId=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}