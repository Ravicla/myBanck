package view;

import business.UsuarioBusiness;
import model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Login extends JFrame{

    private JPanel pLogin;
    private JTextField passwordText;
    private JButton loginBtn;
    private JTextField usuarioText;

    UsuarioBusiness usuarioBusiness = new UsuarioBusiness();

    public Login() {
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioText.getText();
                String password = passwordText.getText();
                boolean usuarioCorrecto = false;
                try {
                    Usuario usuarioResult = usuarioBusiness.login(usuario,password);
                    if(usuarioResult!=null) {
                        if (usuarioResult.getCi().equals(usuario)) {
                            usuarioCorrecto = true;
                            Home home = new Home();
                            home.setVisible(true);
                            /*JOptionPane.showMessageDialog(null, "BIENVENIDO!",
                                    "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);*/
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Error de usuario o contraseña!",
                                    "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Usuario no registrado",
                                "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        pLogin.addComponentListener(new ComponentAdapter() {
        });
    }

    public static void main(String[] args) {
        mostrar();
    }
    public static void mostrar() {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().pLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void ocultar() {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().pLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(false);
        frame.hide();
    }


}
