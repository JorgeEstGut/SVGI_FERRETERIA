package Controller;

import Model.Trabajador;
import Model.TrabajadorDAO;
import View.LoginView;
import javax.swing.JOptionPane;

public class LoginController {
    private LoginView vista;
    private TrabajadorDAO dao;

    public LoginController(LoginView vista, TrabajadorDAO dao) {
        this.vista = vista;
        this.dao = dao;
        initController();
    }

    private void initController() {
        vista.getBtnIngresar().addActionListener(e -> login());
        vista.getTxtClave().addActionListener(e -> vista.getBtnIngresar().doClick());
    }

    private void login() {
        String usuario = vista.getTxtUsuario().getText();
        String clave = new String(vista.getTxtClave().getPassword());

        Trabajador t = dao.login(usuario, clave);

        if (t != null) {
            JOptionPane.showMessageDialog(vista,
                "Usted ha accedido exitosamente como " + t.getRol(),
                "Login Exitoso",
                JOptionPane.INFORMATION_MESSAGE);
            
            View.frmMenu menu = new View.frmMenu(t.getRol());
            menu.setVisible(true);
            
            vista.dispose();
        } else {
            JOptionPane.showMessageDialog(vista,
                "Usuario o contraseña incorrectos",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
