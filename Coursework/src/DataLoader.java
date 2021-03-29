import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataLoader {
    public static DataFrame loadData(String fileName) {

        // Reading file
        File data = new File(fileName);
        DataFrame dataLoaded = new DataFrame();
        try {
            Scanner sc = new Scanner(data);
            String[] columnNames = new String[0];
            if (sc.hasNextLine()) { // read for the column headers
                String line = sc.nextLine();
                columnNames = line.split(",");
                for (String columnName : columnNames) {
                    dataLoaded.addColumn(columnName);
                }
            }

            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                if (line.endsWith(",")) {
                    line += " ";
                }
                String[] rowValues = line.split(",");    // Separating info by spliting commas
//				 if (rowValues.length<20) {
//					 System.out.println(line);
//					 System.out.println(rowValues[18]);
//				 }
                int columnIndex = 0;
                for (String rowValue : rowValues) {
                    dataLoaded.addValue(columnNames[columnIndex], rowValue);
                    columnIndex++;
                }

            }


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            return null;
        }

        return dataLoaded;

    }
}
