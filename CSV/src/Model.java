package CSV.src;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Model extends AbstractTableModel {
    private String[] columnNames = { "Nr. lot", "%Ingrasamant%", "%CaSO4%",
            "Incepere fructificare", "Recoltare", "Cantitate"};
    private ArrayList<String[]> Data =  new ArrayList<>();

    public void AddCSVData(ArrayList<String[]> DataIn) {
        this.Data = DataIn;
        this.fireTableDataChanged();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public int getRowCount() {
        return Data.size();
    }
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    @Override
    public Object getValueAt(int row, int col)
    {
        return Data.get(row)[col];
    }

    public DefaultTableModel convertToDefault() {
        int rows = getRowCount();
        int coll = getColumnCount();

        Object[][] data_frfr = new Object[rows][coll];
        Object[] names_frfr = new Object[coll];

        for(int i = 0; i < coll; i++) {
            names_frfr[i] = getColumnName(i);
        }

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < coll; j++) {
                data_frfr[i][j] = getValueAt(i, j);
            }
        }

        return new DefaultTableModel(data_frfr, names_frfr);
    }
}
