package Model;

public class Trabajador {
    private int id_trabajador;
    private String usuario;
    private String clave;
    private String rol;

    // Constructor vacío
    public Trabajador() {}

    // Constructor con parámetros
    public Trabajador(int id_trabajador, String usuario, String clave, String rol) {
        this.id_trabajador = id_trabajador;
        this.usuario = usuario;
        this.clave = clave;
        this.rol = rol;
    }

    // Getters y setters
    public int getId_trabajador() { return id_trabajador; }
    public void setId_trabajador(int id_trabajador) { this.id_trabajador = id_trabajador; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}
