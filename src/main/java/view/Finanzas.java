package view;

import javax.swing.*;

public class Finanzas extends JFrame{
    private JPanel pFinanza;


    public void setVisible(boolean b) {
        JFrame frame = new JFrame("Finanzas");
        frame.setContentPane(new Finanzas().pFinanza);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
