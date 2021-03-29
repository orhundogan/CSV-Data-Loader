import java.util.ArrayList;

public class Column {
    private String name;
    private ArrayList<String> rows;

    public Column(String name) {
        this.name = name;
        rows = new ArrayList<String>();
    }

    // Adding row value
    public void addRowValue(String row) {
        rows.add(row);
    }

    // Getting name of column
    public String getName() {
        return name;
    }

    // Size of column
    public int getSize() {
        return rows.size();
    }

    public String getRowValue(int index) {
        return rows.get(index).trim();
    }

    public void setRowValue(int index, String value) {
        rows.set(index, value);
    }

}
