package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * CLASE UTILIDAD - DBConnection
 * Centraliza la configuración y conexión con la Base de Datos MySQL
 * Método estático para obtener conexiones desde cualquier parte del código
 */
public class DBConnection {

    // Credenciales y ubicación de la BD
    private static final String URL = "jdbc:mysql://localhost:3306/chinook";
    private static final String USER = "miappuser";
    private static final String PASSWORD = "1234";

    /**
     * Obtiene una conexión activa a la BD MySQL
     * Se usa con try-with-resources para cierre automático
     * @return Conexión a la BD
     * @throws Exception Si hay error de conexión
     */
    public static Connection getConnection() throws Exception {
        // Cargar driver de MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Retornar conexión con credenciales
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
