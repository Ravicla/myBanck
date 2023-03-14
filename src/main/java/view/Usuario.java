package view;

import business.UsuarioBusiness;
import utils.TableModelUtils;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.sql.*;

public class Usuario extends JFrame {
    private JPanel pUsuario;
    private JTextField ciText;
    private JTextField nombreText;
    private JTextField apellidoTtext;
    private JTextField correoText;
    private JTextField telefonoText;
    private JTextField passwordText;
    private JButton ingresarBtn;
    private JTextField estatusText;
    private JTable tableUsuarios;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnSalir;

    UsuarioBusiness usuarioBusiness = new UsuarioBusiness();

    public Usuario() {
        cargarListaUsuarios();

        ingresarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.Usuario usuario = new model.Usuario();
                usuario.setCi(ciText.getText());
                usuario.setNombre(nombreText.getText());
                usuario.setApellido(apellidoTtext.getText());
                usuario.setCorreo(correoText.getText());
                usuario.setTelefono(telefonoText.getText());
                usuario.setEstatus(estatusText.getText());
                usuario.setPassword(ciText.getText());
                try {
                    boolean resultCreate = usuarioBusiness.create(usuario);
                    if (resultCreate) {
                        JOptionPane.showMessageDialog(null, "Error al crear nuevo usuario!",
                                "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Usario creado de manera correcta!",
                                "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                    }
                    cargarListaUsuarios();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.Usuario usuario = new model.Usuario();
                usuario.setCi(ciText.getText());
                usuario.setNombre(nombreText.getText());
                usuario.setApellido(apellidoTtext.getText());
                usuario.setCorreo(correoText.getText());
                usuario.setTelefono(telefonoText.getText());
                usuario.setEstatus(estatusText.getText());
                //usuario.setPassword(ciText.getText());
                try {
                    boolean usuarioUpdate = usuarioBusiness.update(usuario);
                    if (usuarioUpdate) {
                        JOptionPane.showMessageDialog(null, "Error al editar el usuario!",
                                "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Usario editado de manera correcta!",
                                "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                    }
                    cargarListaUsuarios();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });



        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.Usuario usuario = new model.Usuario();
                usuario.setCi(ciText.getText());
                usuario.setNombre(nombreText.getText());
                usuario.setApellido(apellidoTtext.getText());
                usuario.setCorreo(correoText.getText());
                usuario.setTelefono(telefonoText.getText());
                usuario.setEstatus(estatusText.getText());
                try {
                    boolean usuarioDelete = usuarioBusiness.delete(usuario);
                    if (usuarioDelete) {
                        JOptionPane.showMessageDialog(null, "Error al eliminar el usuario!",
                                "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Usario eliminado de manera correcta!",
                                "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                    }
                    cargarListaUsuarios();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ciText.setText("");
               nombreText.setText("");
               apellidoTtext.setText("");
               correoText.setText("");
               telefonoText.setText("");
               estatusText.setText("");
               //passwordText.setText("");
            }
        });

        pUsuario.addComponentListener(new ComponentAdapter() {
        });



        tableUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = tableUsuarios.getSelectedRow();
                TableModel model = tableUsuarios.getModel();
                ciText.setText(model.getValueAt(index,1).toString());
                nombreText.setText(model.getValueAt(index,2).toString());
                apellidoTtext.setText(model.getValueAt(index,3).toString());
                correoText.setText(model.getValueAt(index,4).toString());
                telefonoText.setText(model.getValueAt(index,5).toString());
                estatusText.setText(model.getValueAt(index,6).toString());
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home homeView = new Home();
                homeView.setVisible(true);
            }
        });
    }

    public void setVisible(boolean b) {
        JFrame frame = new JFrame("Usuario");
        frame.setContentPane(new Usuario().pUsuario);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void cargarListaUsuarios() {
        ResultSet resultUsuarios= usuarioBusiness.read();
        TableModel tableModel= TableModelUtils.resultSetToTableModel(resultUsuarios);
        tableUsuarios.setModel(tableModel);//SET CUSTOM RENDERER TO TEAMS COLUMN
    }


}
