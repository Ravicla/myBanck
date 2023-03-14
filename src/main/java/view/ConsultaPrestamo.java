package view;

import business.PrestamoBusiness;
import business.UsuarioBusiness;
import com.toedter.calendar.JDateChooser;
import model.Cuota;
import model.Prestamo;
import model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaPrestamo {
    private JComboBox cbxUsuario;
    private JTextField valorPrestamoText;
    private JTextField interesText;
    private JTextField tiempoPrestamoText;
    private JButton buscarBtn;
    private JTable tablePrestamo;
    private JPanel consultaPrestamo;
    private JTextField FechaPrestamotext;
    private JTextField valorCuotaText;
    private JTextField abonoCuotaText;
    private JTextField valorInteresText;
    private JTextField abonoInteresText;
    private JButton pagarCuotaBtn;
    private JButton abonarBtn;

    JDateChooser dateChooserFPrestamo = new JDateChooser();
    PrestamoBusiness prestamoBusiness = new PrestamoBusiness();

    public ConsultaPrestamo(){
        llenarUsuarios();
        buscarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item itemUsuario = (Item) cbxUsuario.getSelectedItem();
                int usuarioId = itemUsuario.getId();
                System.out.println( usuarioId);

                List<Prestamo> prestamos = prestamoBusiness.obtenerPrestamoByUsuarioId(usuarioId);
                int prestamoId = 0;
                for (Prestamo prestamo : prestamos) {
                    prestamoId = prestamo.getPrestamo_id();
                    valorPrestamoText.setText(String.format(String.valueOf(prestamo.getValor_prestamo())));
                    valorPrestamoText.setText(String.valueOf(prestamo.getPrestamo_id()));
                    FechaPrestamotext.setText(String.valueOf(prestamo.getFecha_prestamo()));
                    interesText.setText(String.valueOf(prestamo.getInteres()));
                    valorPrestamoText.setText(String.valueOf(prestamo.getValor_prestamo()));
                    tiempoPrestamoText.setText(String.valueOf(prestamo.getTiempo_prestamo()));
                }
                List<Cuota> cuotas = prestamoBusiness.obtenerCuotaByIdPrestamo(prestamoId);
                cargarCuota(cuotas);
            }
        });


        pagarCuotaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = tablePrestamo.getSelectedRow();
                TableModel model = tablePrestamo.getModel();
                int cuotaId = Integer.parseInt(model.getValueAt(fila, 0).toString());

                Cuota cuota = new Cuota();
                cuota.setPago_capital(true);
                cuota.setPago_interes(true);
                cuota.setAbono_capital(0);
                cuota.setAbono_interes(0);
                cuota.setCuota_id(cuotaId);
                boolean actualizado = prestamoBusiness.updateCuotaByIdCuota(cuota);

                if (actualizado) {
                    System.out.println("No se pudo actualizar la cuota");
                } else {
                    System.out.println("La cuota ha sido actualizada correctamente");
                }
            }
        });

        abonarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = tablePrestamo.getSelectedRow();
                TableModel model = tablePrestamo.getModel();
                int cuotaId = Integer.parseInt(model.getValueAt(fila, 0).toString());

                Cuota cuota = new Cuota();
                cuota.setAbono_capital(Double.parseDouble(abonoCuotaText.getText()));
                cuota.setAbono_interes(Double.parseDouble(abonoInteresText.getText()));
                cuota.setCuota_id(cuotaId);


                boolean actualizado = prestamoBusiness.updateAbonoByIdCuota(cuota);

                if (actualizado) {
                    System.out.println("No se pudo actualizar el abono");
                } else {
                    System.out.println("El abono ha sido actualizada correctamente");
                }

            }
        });

        tablePrestamo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablePrestamo.getSelectedRow();
                TableModel model = tablePrestamo.getModel();
                valorCuotaText.setText(model.getValueAt(fila, 2).toString());
                valorInteresText.setText(model.getValueAt(fila, 4).toString());
            }
        });


        consultaPrestamo.addComponentListener(new ComponentAdapter() {
        });


    }

    private void llenarUsuarios(){
        UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
        ArrayList<model.Usuario> listaUsuarios = usuarioBusiness.getUsuario();

        cbxUsuario.removeAllItems();
        for (Usuario usuario: listaUsuarios) {
            Prestamo prestamo = new Prestamo();
            cbxUsuario.addItem(new Item(usuario.getUsuario_id(), usuario.getNombre() + " " + usuario.getApellido()));
            valorPrestamoText.setText(String.format(String.valueOf(prestamo.getValor_prestamo())));

        }
    }

    public void setVisible(boolean b) {
        JFrame frame = new JFrame("Consulta Prestamo");
        frame.setContentPane(new ConsultaPrestamo().consultaPrestamo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


   public void cargarCuota(List<Cuota> cuotas){
        DefaultTableModel cuotaTableModel = new DefaultTableModel();
            cuotaTableModel.addColumn("CODE");
            cuotaTableModel.addColumn("Fecha Cuota");
            cuotaTableModel.addColumn("Valor Capital");
            cuotaTableModel.addColumn("Pago Capital");
            cuotaTableModel.addColumn("Valor Interes");
            cuotaTableModel.addColumn("Pago Interes");
            cuotaTableModel.addColumn("Abono Capital");
            cuotaTableModel.addColumn("Abono Interes");
            cuotaTableModel.addColumn("SUMA");
            for (Cuota cuota : cuotas) {
                //VALIDADES
                String mensajeCapital = "";
                if (cuota.isPago_capital()) {
                    mensajeCapital = "PAGADO";
                } else if (cuota.getAbono_capital() > 0) {
                    mensajeCapital = "INCOMPLETO";
                } else {
                    mensajeCapital="PENDIENTE";
                }


                String mensajeInteres = "";
                if (cuota.isPago_interes()){
                    mensajeInteres = "PAGADO";
                } else if (cuota.getAbono_interes() > 0) {
                    mensajeInteres = "INCOMPLETO";
                }else {
                    mensajeInteres = "PENDIENTE";
                }


                Object[] fila = {cuota.getCuota_id(), cuota.getFecha_cuota(),
                        cuota.getValor_capital(),mensajeCapital,cuota.getValor_interes(),
                        mensajeInteres,cuota.getAbono_capital(),cuota.getAbono_interes(),
                        cuota.getValor_capital()+cuota.getValor_interes() };
                cuotaTableModel.addRow(fila);
            }

            tablePrestamo.setModel(cuotaTableModel);
    }


}








