package dao;

import model.Artist;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ArtistDAO - Data Access Object para la entidad Artist
 * Gestiona todas las operaciones CRUD (Create, Read, Update, Delete) 
 * de artistas en la base de datos
 */
public class ArtistDAO {

    /**
     * Obtiene la lista completa de todos los artistas de la BD
     * @return Lista de objetos Artist con todos los registros
     */
    public List<Artist> getAllArtists() {
        // Lista para almacenar los artistas obtenidos
        List<Artist> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {
            // Consulta SQL para obtener todos los artistas
            String sql = "SELECT * FROM Artist";
            PreparedStatement ps = con.prepareStatement(sql);

            // Ejecutar la consulta
            ResultSet rs = ps.executeQuery();

            // Iterar sobre cada fila del resultado
            while (rs.next()) {
                // Crear nuevo objeto Artist
                Artist a = new Artist();
                a.setArtistId(rs.getInt("ArtistId"));
                a.setName(rs.getString("Name"));
                // Agregar a la lista
                list.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    /**
     * Obtiene el siguiente ID disponible para un nuevo artista
     * @return El próximo ID a usar en la inserción
     */
    public int getNextArtistId() {
        int nextId = 1; // valor por defecto si la tabla está vacía
        try (Connection con = DBConnection.getConnection()) {
            // Obtener el ID máximo actual
            String sql = "SELECT MAX(ArtistId) AS maxId FROM Artist";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Incrementar el ID máximo en 1
                nextId = rs.getInt("maxId") + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nextId;
    }

    /**
     * Inserta un nuevo artista en la BD
     * @param name El nombre del artista a insertar
     */
    public void addArtist(String name) {
        try (Connection con = DBConnection.getConnection()) {
            // Obtener el siguiente ID disponible
            int id = getNextArtistId();
            // SQL de inserción con parámetros (previene SQL Injection)
            String sql = "INSERT INTO Artist(ArtistId, Name) VALUES(?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            // Establecer parámetros
            ps.setInt(1, id);
            ps.setString(2, name);
            // Ejecutar la inserción
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene un artista específico por su ID
     * @param id El ID del artista a buscar
     * @return El objeto Artist si existe, null si no
     */
    public Artist getArtistById(int id) {
        // Variable para almacenar el resultado
        Artist a = null;

        try (Connection con = DBConnection.getConnection()) {
            // Consulta con parámetro WHERE para filtrar por ID
            String sql = "SELECT * FROM Artist WHERE ArtistId=?";
            PreparedStatement ps = con.prepareStatement(sql);

            // Establecer el parámetro ID
            ps.setInt(1, id);

            // Ejecutar la consulta
            ResultSet rs = ps.executeQuery();

            // Si existe el registro, crear el objeto
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

    /**
     * Actualiza el nombre de un artista existente
     * @param id El ID del artista a actualizar
     * @param name El nuevo nombre del artista
     */
    public void updateArtist(int id, String name) {
        try (Connection con = DBConnection.getConnection()) {
            // SQL UPDATE con parámetros
            String sql = "UPDATE Artist SET Name=? WHERE ArtistId=?";
            PreparedStatement ps = con.prepareStatement(sql);

            // Establecer los parámetros (nombre e ID)
            ps.setString(1, name);
            ps.setInt(2, id);

            // Ejecutar la actualización
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un artista de la BD por su ID
     * @param id El ID del artista a eliminar
     */
    public void deleteArtist(int id) {
        try (Connection con = DBConnection.getConnection()) {
            // SQL DELETE con parámetro
            String sql = "DELETE FROM Artist WHERE ArtistId=?";
            PreparedStatement ps = con.prepareStatement(sql);

            // Establecer el parámetro ID
            ps.setInt(1, id);
            // Ejecutar la eliminación
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}