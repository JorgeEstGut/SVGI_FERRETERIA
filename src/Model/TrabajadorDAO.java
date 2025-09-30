package Model;

import java.sql.*;

public class TrabajadorDAO {

    public Trabajador login(String usuario, String clave) {
        Trabajador t = null;
        String sql = "SELECT t.id_trabajador, t.usuario, t.clave, r.nombre_rol " +
                     "FROM trabajadores t " +
                     "JOIN roles r ON t.id_rol = r.id_rol " +
                     "WHERE t.usuario=? AND t.clave=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.setString(2, clave);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                t = new Trabajador(
                    rs.getInt("id_trabajador"),
                    rs.getString("usuario"),
                    rs.getString("clave"),
                    rs.getString("nombre_rol")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }
}
