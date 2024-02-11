import INTERFACE.src.InterfataActiv;
import INTERFACE.src.InterfataArhiva;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.Serial;

public class Meniu extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private final JPanel Meniu;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }

        Meniu meniu = new Meniu();
        meniu.setVisible(true);
        meniu.setResizable(false);
    }

    public Meniu() {
        setTitle("FungusForge");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 410, 230);
        Meniu = new JPanel();
        Meniu.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(Meniu);
        Meniu.setLayout(null);

        JButton ButonActiva = new JButton("Activa");
        ButonActiva.addActionListener(e -> {

            InterfataActiv frameActiva = new InterfataActiv();
            frameActiva.setVisible(true);
            frameActiva.setResizable(false);

            frameActiva.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    if (JOptionPane.showConfirmDialog(frameActiva,
                            "Datele introduse si nearhivate se vor pierde!", "Doriti sa inchideti?",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                        File f = new File("activa.csv");
                        f.delete();
                    }
                }
            });
        });
        ButonActiva.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        ButonActiva.setBounds(75, 100, 100, 50);
        Meniu.add(ButonActiva);

        JButton ButonArhiva = new JButton("Arhiva");
        ButonArhiva.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        ButonArhiva.addActionListener(e -> {
            InterfataArhiva frameArhiva = new InterfataArhiva();
            frameArhiva.setVisible(true);
            frameArhiva.setResizable(false);

            frameArhiva.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    if (JOptionPane.showConfirmDialog(frameArhiva,
                            "Doriti sa inchideti?", "",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    }
                }
            });
        });
        ButonArhiva.setBounds(220, 100, 100, 50);
        Meniu.add(ButonArhiva);

        JLabel Text = new JLabel("Ce doriti sa acesati?");
        Text.setHorizontalAlignment(SwingConstants.CENTER);
        Text.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        Text.setBounds(75, 30, 245, 56);
        Meniu.add(Text);
    }
}