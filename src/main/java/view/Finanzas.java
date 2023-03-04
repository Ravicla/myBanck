package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Finanzas extends JFrame{
    private JPanel pFinanza;
    private JButton pretsamoBtn;
    private JButton aportesButton;
    private JButton btnRegresar;


    public Finanzas() {
        aportesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Aportes aportesView = new Aportes();
                aportesView.setVisible(true);

            }
        });
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home homeView = new Home();
                homeView.setVisible(true);
            }
        });
        pretsamoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Prestamos prestamosView = new Prestamos();
                prestamosView.setVisible(true);
            }
        });
    }






    public void setVisible(boolean b) {
        JFrame frame = new JFrame("Finanzas");
        frame.setContentPane(new Finanzas().pFinanza);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
