package Model;

import java.sql.*;

public class TrabajadorDAO {

   public Trabajador login(String usuario, String clave) {
        Trabajador t = null;
        String sql = "SELECT t.id_trabajador, r.id_rol, t.nombre, t.usuario, t.clave, r.nombre_rol " +
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
                    rs.getInt("id_rol"),          // ← lo nuevo
                    rs.getString("usuario"),
                    rs.getString("clave"),
                    rs.getString("nombre_rol"),
                    rs.getString("nombre")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    
    // Listar todos los trabajadores con su rol
    public java.util.List<Trabajador> listar() {
        java.util.List<Trabajador> lista = new java.util.ArrayList<>();
        String sql = "SELECT t.id_trabajador, t.nombre, t.usuario, t.clave, r.nombre_rol " +
                     "FROM trabajadores t " +
                     "JOIN roles r ON t.id_rol = r.id_rol";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Trabajador t = new Trabajador();
                t.setId_trabajador(rs.getInt("id_trabajador"));
                t.setNombre(rs.getString("nombre"));
                t.setUsuario(rs.getString("usuario"));
                t.setClave(rs.getString("clave"));
                t.setRol(rs.getString("nombre_rol"));
                lista.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    // Insertar un nuevo trabajador
    public boolean insertar(Trabajador t) {
        String sql = "INSERT INTO trabajadores (nombre, id_rol, usuario, clave) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getNombre());
            ps.setInt(2, t.getId_rol());
            ps.setString(3, t.getUsuario());
            ps.setString(4, t.getClave());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    
    // Actualizar trabajador
    public boolean actualizar(Trabajador t, int idRol) {
        String sql = "UPDATE trabajadores SET usuario=?, clave=?, id_rol=? WHERE id_trabajador=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getUsuario());
            ps.setString(2, t.getClave());
            ps.setInt(3, idRol);
            ps.setInt(4, t.getId_trabajador());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar trabajador
    public boolean eliminar(int idTrabajador) {
        String sql = "DELETE FROM trabajadores WHERE id_trabajador=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idTrabajador);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //Listar roles
    public java.util.List<Rol> listarRoles() {
        java.util.List<Rol> lista = new java.util.ArrayList<>();
        String sql = "SELECT * FROM roles";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Rol rol = new Rol();
                rol.setId_rol(rs.getInt("id_rol"));
                rol.setNombre_rol(rs.getString("nombre_rol"));
                lista.add(rol);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


}
