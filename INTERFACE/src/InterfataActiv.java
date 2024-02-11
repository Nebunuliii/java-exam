package INTERFACE.src;

import java.io.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.Random;

public class InterfataActiv extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;
    private final JTable TabelActiv;
    private final JTextField ZonaNrLot;
    private final JTextField ZonaIngrasamant;
    private final JTextField ZonaCaSO4;
    private final JTextField ZonaStadiu;
    private final JTextField ZonaData;
    SimpleDateFormat fdata = new SimpleDateFormat("dd/MM/yyyy");

    public InterfataActiv() {

        setTitle("FungusForge-Activa");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 715, 530);
        JPanel interfataActiv = new JPanel();
        interfataActiv.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(interfataActiv);
        interfataActiv.setLayout(null);

        //Zona Tabel Scroll
        JScrollPane ZonaTabelScroll = new JScrollPane();
        ZonaTabelScroll.setBounds(184, 81, 494, 369);
        interfataActiv.add(ZonaTabelScroll);

        TabelActiv = new JTable();
        TabelActiv.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "Nr.Lot", "%Ingrasamant", "%CaSO4", "Stadiu", "Data incepere fructificare"
                }
        ));
        TabelActiv.setAutoCreateRowSorter(true);
        DefaultTableModel model = (DefaultTableModel)TabelActiv.getModel();

        ZonaTabelScroll.setViewportView(TabelActiv);

        JLabel Titlu = new JLabel("Tabel Ciuperci Activa");
        Titlu.setFont(new Font("Times New Roman", Font.BOLD, 24));
        Titlu.setHorizontalAlignment(SwingConstants.CENTER);
        Titlu.setBounds(184, 14, 494, 25);
        interfataActiv.add(Titlu);

        //Zona DateAdaugare
        JPanel PanouDateAdaugare = new JPanel();
        PanouDateAdaugare.setBounds(184, 461, 494, 20);
        interfataActiv.add(PanouDateAdaugare);
        PanouDateAdaugare.setLayout(null);

        ZonaNrLot = new JTextField();
        ZonaNrLot.setColumns(10);
        ZonaNrLot.setBounds(0, 0, 100, 20);
        PanouDateAdaugare.add(ZonaNrLot);

        ZonaIngrasamant = new JTextField();
        ZonaIngrasamant.setColumns(10);
        ZonaIngrasamant.setBounds(98, 0, 100, 20);
        PanouDateAdaugare.add(ZonaIngrasamant);

        ZonaCaSO4 = new JTextField();
        ZonaCaSO4.setColumns(10);
        ZonaCaSO4.setBounds(197, 0, 100, 20);
        PanouDateAdaugare.add(ZonaCaSO4);

        ZonaStadiu = new JTextField();
        ZonaStadiu.setColumns(10);
        ZonaStadiu.setBounds(296, 0, 100, 20);
        PanouDateAdaugare.add(ZonaStadiu);

        ZonaData = new JTextField();
        ZonaData.setColumns(10);
        ZonaData.setBounds(394, 0, 100, 20);
        PanouDateAdaugare.add(ZonaData);

        //Zona Butoane
        JPanel PanouButoane = new JPanel();
        PanouButoane.setBounds(10, 81, 164, 369);
        interfataActiv.add(PanouButoane);
        PanouButoane.setLayout(null);

        JButton Adaugare = new JButton("Add");
        Adaugare.setFont(new Font("Time New Roman", Font.PLAIN, 11));
        Adaugare.addActionListener(e -> {
            if(ZonaNrLot.getText().isEmpty() || ZonaIngrasamant.getText().isEmpty() || ZonaCaSO4.getText().isEmpty() || ZonaStadiu.getText().isEmpty() || ZonaData.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Reintroduceti datele!");
            }
            else
            {
                Object[] data = new Object[]{ZonaNrLot.getText(),ZonaIngrasamant.getText(),
                        ZonaCaSO4.getText(),ZonaStadiu.getText(),ZonaData.getText()};
                model.addRow(data);
                JOptionPane.showMessageDialog(null,
                        "Datele au fost adaugate cu success!");
                ZonaNrLot.setText("");
                ZonaIngrasamant.setText("");
                ZonaCaSO4.setText("");
                ZonaStadiu.setText("");
                ZonaData.setText("");

                try {
                    FileWriter csv = new FileWriter("activa.csv");

                    csv.write(model.getColumnName(0));
                    for (int i = 1; i < model.getColumnCount(); i++) {
                        csv.write("," + model.getColumnName(i));
                    }

                    csv.write("\n");

                    for (int i = 0; i < model.getRowCount(); i++) {
                        csv.write(model.getValueAt(i, 0).toString());
                        for (int j = 1; j < model.getColumnCount(); j++) {
                            csv.write("," + model.getValueAt(i, j).toString());
                        }
                        csv.write("\n");
                    }

                    csv.close();
                } catch (IOException ignored) {
                }
            }
        });
        Adaugare.setBounds(41, 0, 89, 23);
        PanouButoane.add(Adaugare);

        JButton Edit = new JButton("Edit");
        Edit.setFont(new Font("Time New Roman", Font.PLAIN, 11));
        Edit.addActionListener(e -> {

            try {
                FileWriter csv = new FileWriter("activa.csv");

                csv.write(model.getColumnName(0));
                for (int i = 1; i < model.getColumnCount(); i++) {
                    csv.write("," + model.getColumnName(i));
                }

                csv.write("\n");

                for (int i = 0; i < model.getRowCount(); i++) {
                    csv.write(model.getValueAt(i, 0).toString());
                    for (int j = 1; j < model.getColumnCount(); j++) {
                        csv.write("," + model.getValueAt(i, j).toString());
                    }
                    csv.write("\n");
                }

                csv.close();
            } catch (IOException ignored) {
            }
        });
        Edit.setBounds(41, 110, 89, 23);
        PanouButoane.add(Edit);

        JButton Delete = new JButton("Delete");
        Delete.setFont(new Font("Time New Roman", Font.PLAIN, 11));
        Delete.addActionListener(e -> {
            if(TabelActiv.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Selectati minim un rand!");
            }
            else {
                int[] selectedRows = TabelActiv.getSelectedRows();
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    model.removeRow(selectedRows[i]);
                }

                JOptionPane.showMessageDialog(null, "Operatia executata cu success!");

                try {
                    FileWriter csv = new FileWriter("activa.csv");

                    csv.write(model.getColumnName(0));
                    for (int i = 1; i < model.getColumnCount(); i++) {
                        csv.write("," + model.getColumnName(i));
                    }

                    csv.write("\n");

                    for (int i = 0; i < model.getRowCount(); i++) {
                        csv.write(model.getValueAt(i, 0).toString());
                        for (int j = 1; j < model.getColumnCount(); j++) {
                            csv.write("," + model.getValueAt(i, j).toString());
                        }
                        csv.write("\n");
                    }

                    csv.close();
                } catch (IOException ignored) {
                }
            }
        });
        Delete.setBounds(41, 227, 89, 23);
        PanouButoane.add(Delete);

        JButton Arhivare = new JButton("Arhivare");
        Arhivare.setFont(new Font("Time New Roman", Font.PLAIN, 11));
        Arhivare.addActionListener(e -> {
            if(TabelActiv.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Selectati minim un rand!");
            }
            else {
                try {
                    Random rand = new Random();
                    int rand_int = 12 + rand.nextInt(13);

                    String timp;
                    Date timpD;
                    Calendar timpC= Calendar.getInstance();

                    PrintWriter csv_arh = new PrintWriter(new FileWriter("arhiva.csv", true));

                    int[] selectedRows = TabelActiv.getSelectedRows();


                    for (int i = selectedRows.length - 1; i >= 0; i--) {
                        timp = model.getValueAt(selectedRows[i], 4).toString();
                        timpD = fdata.parse(timp);
                        timpC.setTime(timpD);
                        timpC.add(Calendar.DAY_OF_MONTH, 7);
                        timpD = timpC.getTime();

                        csv_arh.write(model.getValueAt(selectedRows[i], 0) + ","
                                       + model.getValueAt(selectedRows[i], 1) + ","
                                       + model.getValueAt(selectedRows[i], 2) + ","
                                       + model.getValueAt(selectedRows[i], 4) + ","
                                       + fdata.format(timpD) + ","
                                       + rand_int + "\n");

                        model.removeRow(selectedRows[i]);
                    }

                    csv_arh.close();

                    FileWriter csv_act = new FileWriter("activa.csv");

                    csv_act.write(model.getColumnName(0));
                    for (int i = 1; i < model.getColumnCount(); i++) {
                        csv_act.write("," + model.getColumnName(i));
                    }

                    csv_act.write("\n");

                    for (int i = 0; i < model.getRowCount(); i++) {
                        csv_act.write(model.getValueAt(i, 0).toString());
                        for (int j = 1; j < model.getColumnCount(); j++) {
                            csv_act.write("," + model.getValueAt(i, j).toString());
                        }
                        csv_act.write("\n");
                    }

                    csv_act.close();
                } catch (IOException | ParseException ignored) {
                }
            }
        });
        Arhivare.setBounds(41, 346, 89, 23);
        PanouButoane.add(Arhivare);

        //Zona Search
        JPanel ZonaSearch = new JPanel();
        ZonaSearch.setBounds(184, 50, 199, 20);
        interfataActiv.add(ZonaSearch);
        ZonaSearch.setLayout(null);

        JTextField textSearch = new JTextField();
        textSearch.setColumns(10);
        textSearch.setBounds(0, 0, 100, 20);
        ZonaSearch.add(textSearch);

        JButton SearchButton = new JButton("Search");
        SearchButton.setFont(new Font("Time New Roman", Font.PLAIN, 11));
        SearchButton.addActionListener(e -> {
            if(textSearch.getText().length() == 3 || textSearch.getText().isEmpty())
            {
                TableRowSorter<DefaultTableModel> search = new TableRowSorter<>(model);
                TabelActiv.setRowSorter(search);
                search.setRowFilter(RowFilter.regexFilter(textSearch.getText().trim(),0));
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Introduceti primele 3 caractere!");
            }
        });
        SearchButton.setBounds(110, 0, 89, 20);
        ZonaSearch.add(SearchButton);
    }
}