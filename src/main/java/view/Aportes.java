package view;

import business.AporteBusiness;
import business.UsuarioBusiness;
import com.toedter.calendar.JDateChooser;
import model.Aporte;
import model.TipoIngreso;
import model.Usuario;
import utils.TableModelUtils;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Aportes  extends JFrame {
    private JPanel pAporte;
    private JComboBox cbxTipo;
    private JButton btnGuardar;
    private JTable tableAporte;
    private JComboBox cbxUsuario;
    private JTextField valorText;
    private JPanel pDate;
    private JButton editarButton;
    private JButton limpiarButton;
    private JButton borrarButton;
    private JTextField codigoText;
    JDateChooser dateChooser = new JDateChooser();

    AporteBusiness aporteBusiness = new AporteBusiness();

    public Aportes() {
        cargarListaAportes();

        llenarUsuarios();
        llenarTipoIngreso();

        //para agregar el date chosser
        pDate.add(dateChooser);

         btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.Aporte aporte = new model.Aporte();
                Item itemTipoIngreso = (Item)cbxTipo.getSelectedItem();
                aporte.setTipoIngreso(itemTipoIngreso.getId());

                Item itemUsuario = (Item)cbxUsuario.getSelectedItem();
                aporte.setUsuario(itemUsuario.getId());

                Date fecha = dateChooser.getDate();
                aporte.setFecha_aporte(fecha);

                double valorDecimal = Double.parseDouble(valorText.getText());
                aporte.setValor_aporte(valorDecimal);

                try {
                    boolean resultCreate = aporteBusiness.create(aporte);
                    if (resultCreate){
                        JOptionPane.showMessageDialog(null, "Error al crear nuevo aporte!",
                                "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Aporte creado de manera correcta!",
                                "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                    }
                    cargarListaAportes();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.Aporte aporte = new Aporte();
                aporte.setTipoIngreso(1);
                aporte.setUsuario(2);
                aporte.setFecha_aporte(dateChooser.getDate());
                aporte.setAporte_id(Integer.parseInt(codigoText.getText()));
                aporte.setValor_aporte(Double.parseDouble(valorText.getText()));

                try {
                    boolean usuarioUpdate = aporteBusiness.update(aporte);
                    if (usuarioUpdate) {
                        JOptionPane.showMessageDialog(null, "Error al editar un aporte!",
                                "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Aporte editado de manera correcta!",
                                "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                    }
                    cargarListaAportes();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.Aporte aporte = new Aporte();
                aporte.setAporte_id(Integer.parseInt(codigoText.getText()));
                try {
                    boolean usuarioDelete = aporteBusiness.delete(aporte);
                    if (usuarioDelete) {
                        JOptionPane.showMessageDialog(null, "Error al borrar un aporte!",
                                "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Aporte borrado de manera correcta!",
                                "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                    }
                    cargarListaAportes();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valorText.setText("");
                codigoText.setText("");
            }
        });

        pAporte.addComponentListener(new ComponentAdapter() {
        });


        tableAporte.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = tableAporte.getSelectedRow();
                TableModel model = tableAporte.getModel();
                cbxTipo.setSelectedIndex(3);
                cbxUsuario.setSelectedItem(3);
                //dateChooser;
                valorText.setText(model.getValueAt(index, 2).toString());
                codigoText.setText(model.getValueAt(index, 4).toString());
            }
        });
    }

    private void llenarUsuarios(){
        UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
        ArrayList<Usuario> listaUsuarios = usuarioBusiness.getUsuario();
        cbxUsuario.removeAllItems();
        for (Usuario usuario: listaUsuarios) {
            cbxUsuario.addItem(new Item(usuario.getUsuario_id(), usuario.getNombre() + " " + usuario.getApellido()));
        }
    }

    private void llenarTipoIngreso(){
        UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
        ArrayList<TipoIngreso>listaTipoIngreso = usuarioBusiness.getTipoIngreso();
        cbxTipo.removeAllItems();
        for (TipoIngreso tipoIngreso: listaTipoIngreso){
            cbxTipo.addItem(new Item(tipoIngreso.getTipoIngreso_id(), tipoIngreso.getDescripcion()));
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

    public void cargarListaAportes() {
        ResultSet resultAportes = aporteBusiness.read();
        TableModel tableModel = TableModelUtils.resultSetToTableModel(resultAportes);
        tableAporte.setModel(tableModel);//SET CUSTOM RENDERER TO TEAMS COLUMN
    }
}
