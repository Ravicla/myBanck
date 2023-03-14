package view;

import business.PrestamoBusiness;
import business.UsuarioBusiness;
import com.toedter.calendar.JDateChooser;
import model.*;
import model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Prestamos extends JFrame{
    private JComboBox cbxUsuario;
    private JTextField valorPrestamoText;
    private JTextField interesText;
    private JTextField tiempoPrestamoText;
    private JButton simularButton;
    private JPanel pPrestamo;
    private JPanel fechaPrestamoP;
    private JTable tableSimulacion;
    private JButton generarPrestamoBtn;
    List<SimulacionCuota> resultadoSimulacion;

    JDateChooser dateChooserFPrestamo = new JDateChooser();
    PrestamoBusiness prestamoBusiness = new PrestamoBusiness();

    public Prestamos(){
        llenarUsuarios();
        fechaPrestamoP.add(dateChooserFPrestamo);

        /*simularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.Prestamo prestamo = new model.Prestamo();
                Item itemUsuario = (Item) cbxUsuario.getSelectedItem();
                prestamo.setUsuario(itemUsuario.getId());
                double valorPrestamo = Double.parseDouble(valorPrestamoText.getText());
                prestamo.setValor_prestamo(valorPrestamo);
                int vInteres = Integer.parseInt(interesText.getText());
                prestamo.setInteres(vInteres);
                Date fechaPrestamo = dateChooserFPrestamo.getDate();
                prestamo.setFecha_prestamo(fechaPrestamo);
                int tiempoPrestamo = Integer.parseInt(tiempoPrestamoText.getText());
                prestamo.setTiempo_prestamo(tiempoPrestamo);
                try {
                    boolean resultCreate = prestamoBusiness.createPrestamo(prestamo);
                    if (resultCreate){
                        JOptionPane.showMessageDialog(null, "Error al crear nuevo prestamo!",
                                "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Prestamo creado de manera correcta!",
                                "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                    }
                    cargarPrestamo();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });*/

        simularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Simulacion simulacion  = new Simulacion();

                Item itemUsuario = (Item) cbxUsuario.getSelectedItem();
                simulacion.setUsuario(itemUsuario.getId());

                simulacion.setValor(Double.parseDouble(valorPrestamoText.getText()));

                simulacion.setFecha(dateChooserFPrestamo.getDate());
                simulacion.setTiempo(Integer.parseInt(tiempoPrestamoText.getText()));

                resultadoSimulacion = prestamoBusiness.calcularSimulacion(simulacion);
                cargarSimulacion(resultadoSimulacion);
            }
        });

        generarPrestamoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.Prestamo prestamo = new model.Prestamo();
                Item itemUsuario = (Item) cbxUsuario.getSelectedItem();
                prestamo.setUsuario(itemUsuario.getId());

                Date fechaPrestamo = dateChooserFPrestamo.getDate();
                prestamo.setFecha_prestamo(fechaPrestamo);

                int vInteres = Integer.parseInt(interesText.getText());
                prestamo.setInteres(vInteres);

                double valorPrestamo = Double.parseDouble(valorPrestamoText.getText());
                prestamo.setValor_prestamo(valorPrestamo);

                int tiempoPrestamo = Integer.parseInt(tiempoPrestamoText.getText());
                prestamo.setTiempo_prestamo(tiempoPrestamo);

                try {
                    boolean resultCreate = prestamoBusiness.createPrestamo(prestamo);

                    if (!resultCreate) {
                        //aqui llamar al metodo q nos va a devolver el id del prestamo
                        int idPrestamo = prestamoBusiness.obtenerIdPrestamo(prestamo);
                        System.out.println(idPrestamo);
                        for (SimulacionCuota simulacionCuota : resultadoSimulacion) {
                            System.out.println(simulacionCuota.getCuotaCapital());

                            Cuota cuota = new Cuota();
                            cuota.setFecha_cuota(simulacionCuota.getFecha());
                            cuota.setValor_capital(simulacionCuota.getCuotaCapital());
                            cuota.setPago_capital(false);
                            cuota.setValor_interes(simulacionCuota.getInteresCapital());
                            cuota.setPago_interes(false);
                            cuota.setAbono_capital(0);
                            cuota.setAbono_interes(0);
                            cuota.setPrestamo(idPrestamo);

                            prestamoBusiness.createCuota(cuota);

                        }

                    }

                    /*if (resultCreate){
                        JOptionPane.showMessageDialog(null, "Error al crear nuevo prestamo!",
                                "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Prestamo creado de manera correcta!",
                                "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                    }*/

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        pPrestamo.addComponentListener(new ComponentAdapter() {
        });
    }


    private void llenarUsuarios(){
        UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
        ArrayList<model.Usuario> listaUsuarios = usuarioBusiness.getUsuario();
        cbxUsuario.removeAllItems();
        Map<Integer, String> mapa = new HashMap<Integer, String>();
        for (Usuario usuario: listaUsuarios) {
            cbxUsuario.addItem(new Item(usuario.getUsuario_id(), usuario.getNombre() + " " + usuario.getApellido()));
        }
    }

    public void setVisible(boolean b) {
        JFrame frame = new JFrame("Prestamos");
        frame.setContentPane(new Prestamos().pPrestamo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void cargarSimulacion(List<SimulacionCuota> simulacion) {
        // Crear una nueva instancia de DefaultTableModel
        DefaultTableModel tableModel = new DefaultTableModel();
        // Agregar las columnas a la tabla
        tableModel.addColumn("Descripción");
        tableModel.addColumn("Cuota de Capital");
        tableModel.addColumn("Cuota de Interés");
        tableModel.addColumn("Total de Cuota");
        tableModel.addColumn("Fecha");
        // Recorrer la simulación y agregar las filas a la tabla
        for (SimulacionCuota cuota : simulacion) {
            Object[] fila = {cuota.getDescripcion(), cuota.getCuotaCapital(), cuota.getInteresCapital(), cuota.getTotalCuota(), cuota.getFecha()};
            interesText.setText(String.format(String.valueOf(cuota.getInteres())));
            tableModel.addRow(fila);
        }
        tableSimulacion.setModel(tableModel);
    }
}
