package INTERFACE.src;

import CSV.src.CSVFile;
import CSV.src.Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class InterfataArhiva extends JFrame{
    @Serial
    private static final long serialVersionUID = 1L;
    private final JPanel InterfataArhiva;
    private final JTextField textSearch;
    private final JTable TabelArhiva;
    public InterfataArhiva() {
        setTitle("FungusForge-Arhiva");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 715, 530);
        InterfataArhiva = new JPanel();
        InterfataArhiva.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(InterfataArhiva);
        InterfataArhiva.setLayout(null);


        JScrollPane ZonaTabelScroll = new JScrollPane();
        ZonaTabelScroll.setBounds(10, 76, 679, 404);
        InterfataArhiva.add(ZonaTabelScroll);

        CSVFile csv = new CSVFile();
        File file = new File("arhiva.csv");
        ArrayList<String[]> data = csv.ReadCSV(file);
        Model model = new Model();
        model.AddCSVData(data);

        DefaultTableModel model_default = model.convertToDefault();

        TabelArhiva = new JTable();
        TabelArhiva.setModel(model_default);
        ZonaTabelScroll.setViewportView(TabelArhiva);
        TabelArhiva.setAutoCreateRowSorter(true);

        JLabel Titlu = new JLabel("Tabel Ciuperci Arhiva");
        Titlu.setBounds(10, 14, 679, 25);
        Titlu.setFont(new Font("Time New Roman", Font.BOLD, 24));
        Titlu.setHorizontalAlignment(SwingConstants.CENTER);
        InterfataArhiva.add(Titlu);

        JPanel ZonaSearch = new JPanel();
        ZonaSearch.setLayout(null);
        ZonaSearch.setBounds(10, 45, 199, 20);
        InterfataArhiva.add(ZonaSearch);

        textSearch = new JTextField();
        textSearch.setColumns(10);
        textSearch.setBounds(0, 0, 100, 20);
        ZonaSearch.add(textSearch);

        JButton SearchButton = new JButton("Search");
        SearchButton.setFont(new Font("Time New Roman", Font.PLAIN, 11));
        SearchButton.addActionListener(e -> {
            if(textSearch.getText().length() == 3 || textSearch.getText().isEmpty())
            {
                //DefaultTableModel modelnus = (DefaultTableModel)TabelArhiva.getModel();
                TableRowSorter<DefaultTableModel> search = new TableRowSorter<>((DefaultTableModel) TabelArhiva.getModel());
                TabelArhiva.setRowSorter(search);
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
