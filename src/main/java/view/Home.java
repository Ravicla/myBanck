package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class Home extends JFrame{
    private JPanel pHome;
    private JButton configuracionBtn;
    private JButton usuarioBtn;
    private JButton finazasBtn;

    public Home() {
        usuarioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Usuario usuarioView = new Usuario();
                usuarioView.setVisible(true);
            }
        });
        pHome.addComponentListener(new ComponentAdapter() {
        });



        finazasBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Finanzas finanzas = new Finanzas();
                finanzas.setVisible(true);
            }
        });
        configuracionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Configuracion configuracion = new Configuracion();
                configuracion.setVisible(true);
            }
        });
    }

    public void setVisible(boolean b) {
        JFrame frame = new JFrame("Home");
        frame.setContentPane(new Home().pHome);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}



