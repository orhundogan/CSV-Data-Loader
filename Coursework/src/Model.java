import java.util.ArrayList;

public class Model {
    private DataFrame dataFrame;
    ArrayList<String> selectedColumns = new ArrayList<String>();

    public Model() {
        dataFrame = new DataFrame();
    }

    public String loadData(String fileName) {
        dataFrame = DataLoader.loadData(fileName);
        for (String c : getAllColumns()) {
            selectedColumns.add(c);
        }
        if (dataFrame != null) {
            return dataFrame.toString();
        } else {
            return "File not found";
        }


    }

    //public String getDataFrameValues() {
    //return dataFrame.toString(selectedColumns);
    //}

    // All column names
    public ArrayList<String> getAllColumns() {
        return dataFrame.getColumnNames();
    }

    // Getting selected column names by using checkboxes
    public ArrayList<String> getSelectedColumns() {
        return selectedColumns;
    }

    public void remove(String s) {
        selectedColumns.remove(s);
    }

    public void add(String s) {
        selectedColumns.add(s);
        ArrayList<String> selectedColumns2 = new ArrayList<String>();
        for (String c : getAllColumns()) {
            if (selectedColumns.contains(c)) {
                selectedColumns2.add(c);
            }

        }
        selectedColumns = selectedColumns2;

    }


    // Amount of rows
    public int amount() {
        return dataFrame.getRowCount();
    }

    // Gets the element at index index and column name s
    public String getValue(int index, String s) {
        return dataFrame.getValue(s, index);
    }

    // Finding max
    public String getMax(String column) {
        return dataFrame.getMax(column);
    }

    // Finding min
    public String getMin(String column) {
        return dataFrame.getMin(column);
    }

    // Column count
    public int getColCount() {
        return selectedColumns.size();

    }

    // Row count
    public int getRowCount() {
        return dataFrame.getRowCount();

    }

    // Table data
    public String[][] getTableData() {
        String data[][] = new String[getRowCount()][selectedColumns.size()];
        for (int row = 0; row < getRowCount(); row++) {
            for (int col = 0; col < selectedColumns.size(); col++) {
                data[row][col] = getValue(row, selectedColumns.get(col));
            }
        }
        return data;
    }

    public String[][] getTableData(String searchKeyword, String column) {
        ArrayList<Integer> r = dataFrame.search(searchKeyword, column, selectedColumns);
        System.out.println(r.size());
        String data[][] = new String[r.size()][selectedColumns.size()];
        for (int row = 0; row < r.size(); row++) {
            for (int col = 0; col < selectedColumns.size(); col++) {
                data[row][col] = getValue(r.get(row), selectedColumns.get(col));
            }

        }
        return data;
    }

    // Getting table Columns
    public String[] getTableColumns() {
        String column[] = new String[selectedColumns.size()];
        for (int col = 0; col < selectedColumns.size(); col++) {

            column[col] = selectedColumns.get(col);
        }
        return column;
    }

    // Resetting the table
    public void reset() {
        selectedColumns = new ArrayList<String>();
        for (String c : getAllColumns()) {
            selectedColumns.add(c);
        }
    }


}
