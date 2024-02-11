package CSV.src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class CSVFile {
    private ArrayList<String[]> rows = new ArrayList<>();
    private String[] row;

    public ArrayList<String[]> ReadCSV(File data) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(data));

            boolean loop = true;
            while (loop) {
                String st = br.readLine();
                if(st != null) {
                    row = st.split(",");
                    rows.add(row);
                    loop = true;
                }
                else {
                    loop = false;
                }
            }
        }
        catch (Exception ignored) {}

        return rows;
    }
}
