package view;

import javax.swing.*;
import java.awt.*;

public class Configuracion extends JFrame{
    private JPanel pConfiguracion;


    public void setVisible(boolean b) {
        JFrame frame = new JFrame("Configuracion");
        frame.setContentPane(new Configuracion().pConfiguracion);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
