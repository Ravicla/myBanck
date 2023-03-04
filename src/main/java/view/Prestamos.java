package view;

import business.PrestamoBusiness;
import business.UsuarioBusiness;
import com.toedter.calendar.JDateChooser;
import model.Usuario;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Prestamos extends JFrame{
    private JTextField pagoInteresText;
    private JTextField valorIText;
    private JTextField pagoCapitaText;
    private JTextField valorCText;
    private JComboBox cbxUsuario;
    private JTextField ValorPrestamoText;
    private JTextField InteresText;
    private JTextField fechaPrestamoText;
    private JTextField tiempoPrestamoText;
    private JTextField fechaCuotaText;
    private JTextField valorCapitalText;
    private JTextField valorInteresText;
    private JButton ingresarButton;
    private JPanel pPrestamo;

    JDateChooser dateChooserFPrestamo = new JDateChooser();
    JDateChooser dateChooserFCuota = new JDateChooser();

    PrestamoBusiness prestamoBusiness = new PrestamoBusiness();

    public Prestamos(){
        llenarUsuarios();
    }


    private void llenarUsuarios(){
        UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
        ArrayList<model.Usuario> listaUsuarios = usuarioBusiness.getUsuario();
        cbxUsuario.removeAllItems();
        Map<Integer, String> mapa = new HashMap<Integer, String>();
        for (Usuario usuario: listaUsuarios) {
            mapa.put(usuario.getUsuario_id(), usuario.getNombre() + " " + usuario.getApellido());
        }
        for (Map.Entry<Integer, String> entry : mapa.entrySet()) {
            cbxUsuario.addItem(entry.getValue());
        }
    }




    public void setVisible(boolean b) {
        JFrame frame = new JFrame("Aporte");
        frame.setContentPane(new Prestamos().pPrestamo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}
