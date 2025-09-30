package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:postgresql://localhost:5432/sgvi_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "10247";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
