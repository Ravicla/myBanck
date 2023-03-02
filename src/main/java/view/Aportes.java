package view;

import business.AporteBusiness;
import business.UsuarioBusiness;
import com.toedter.calendar.JDateChooser;
import model.TipoIngreso;
import model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Aportes  extends JFrame {
    private JPanel pAporte;
    private JComboBox cbxTipo;
    private JButton btnGuardar;
    private JTable table1;
    private JComboBox cbxUsuario;
    private JTextField valorText;
    private JPanel pDate;
    JDateChooser dateChooser = new JDateChooser();

    AporteBusiness aporteBusiness = new AporteBusiness();

    public Aportes() {
        llenarUsuarios();
        llenarTipoIngreso();
        //para agregar el date chosser
        pDate.add(dateChooser);
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.Aporte aporte = new model.Aporte();
                aporte.setTipoIngreso(1);
                aporte.setUsuario(1);

                Date fecha=dateChooser.getDate();
                aporte.setFecha_aporte(fecha);
                double valorDecimal = Double.parseDouble(valorText.getText());
                aporte.setValor_aporte(valorDecimal);

                try {
                    boolean resultCreate = aporteBusiness.create(aporte);
                    if (resultCreate){
                        JOptionPane.showMessageDialog(null, "Error al crear nuevo usuario!",
                                "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Usario creado de manera correcta!",
                                "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void llenarUsuarios(){
        UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
        ArrayList<Usuario> listaUsuarios = usuarioBusiness.getUsuario();
        cbxUsuario.removeAllItems();
        Map<Integer, String> mapa = new HashMap<Integer, String>();
        for (Usuario usuario: listaUsuarios) {
            mapa.put(usuario.getUsuario_id(), usuario.getNombre() + " " + usuario.getApellido());
        }
        for (Map.Entry<Integer, String> entry : mapa.entrySet()) {
            cbxUsuario.addItem(entry.getValue());
        }
    }

    private void llenarTipoIngreso(){
        UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
        ArrayList<TipoIngreso>listaTipoIngreso = usuarioBusiness.getTipoIngreso();
        cbxTipo.removeAllItems();
        Map<Integer, String> mapa = new HashMap<Integer, String>();
        for (TipoIngreso tipoIngreso: listaTipoIngreso){
            mapa.put(tipoIngreso.getTipoIngreso_id(), tipoIngreso.getDescripcion());
        }
        for (Map.Entry<Integer, String> entry: mapa.entrySet()){
            cbxTipo.addItem(entry.getValue());
        }
    }

    public void setVisible(boolean b) {
        JFrame frame = new JFrame("Aporte");
        frame.setContentPane(new Aportes().pAporte);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
