import java.util.ArrayList;

public class DataFrame {
    private ArrayList<Column> columns;

    public DataFrame() {
        columns = new ArrayList<Column>();
    }

    // Add new column
    public void addColumn(String name) {
        Column c = new Column(name);
        columns.add(c);
    }

    public void addColumn(Column c) {
        columns.add(c);
    }

    public ArrayList<String> getColumnNames() {
        ArrayList<String> namesList = new ArrayList<String>();
        for (Column c : columns) {
            namesList.add(c.getName());
        }
        return namesList;
    }

    // Amount of rows
    public int getRowCount() {
        if (!columns.isEmpty()) {
            return columns.get(0).getSize();
        } else {
            return -1;
        }
    }

    // Getting the value at a row with column name columnName
    public String getValue(String columnName, int row) {
        for (Column c : columns) {
            if (columnName.equals(c.getName())) {
                return c.getRowValue(row);
            }
        }
        return "";
    }

    public void putValue(String columnName, int row, String value) {
        for (Column c : columns) {
            if (columnName.equals(c.getName())) {
                c.setRowValue(row, value);
            }
        }
    }

    // Adding value
    public void addValue(String columnName, String value) {
        for (Column c : columns) {
            if (columnName.equals(c.getName())) {
                c.addRowValue(value);
            }
        }
    }

    // Turning the content to string
    public String toString() {
        String result = "";
        for (Column c : columns) {
            result += c.getName() + "\t";
        }
        result += "\n";
        for (int row = 0; row < getRowCount(); row++) {
            for (Column c : columns) {
                try {
                    result += getValue(c.getName(), row) + "\t\t";
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            result += "\n";
        }
        result += "\n";

        return result;


    }

    public String toString(ArrayList<String> selectedColumns) {
        String result = "";
        for (int row = 0; row < getRowCount(); row++) {
            for (Column c : columns) {
                if (selectedColumns.contains(c.getName()))
                    try {
                        //result+=getValue(c.getName(),row) + "\t\t";
                        if (getValue(c.getName(), row).equals("")) {
                            result += String.format("%-100s", "o");
                        } else {
                            result += String.format("%-100s", getValue(c.getName(), row));
                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
            }
            result += "\n";
        }
        result += "\n";

        return result;


    }

    // Used for searching in gui
    public ArrayList<Integer> search(String searchKeyword, String column, ArrayList<String> selectedColumns) {


        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int row = 0; row < getRowCount(); row++) {
            if (getValue(column, row).toLowerCase().startsWith(searchKeyword.toLowerCase())) {
                result.add(row);
            }
        }


        return result;


    }

    // Used for finding max
    public String getMax(String column) {
        String max = "";
        for (Column c : columns) {
            if (c.getName().equals(column)) {
                for (int row = 0; row < getRowCount(); row++) {
                    if (max.compareTo(getValue(c.getName(), row)) < 0) {
                        max = getValue(c.getName(), row);
                    }

                }
            }

        }
        return max;
    }

    // Used for finding min
    public String getMin(String column) {
        String min = "ZZZZZZZZZZZZZZZZZ";
        for (Column c : columns) {
            if (c.getName().equals(column)) {
                for (int row = 0; row < getRowCount(); row++) {
                    if (min.compareTo(getValue(c.getName(), row)) > 0 && getValue(c.getName(), row) != "") {
                        min = getValue(c.getName(), row);
                    }

                }
            }

        }
        return min;
    }

    public int getColCount() {
        return columns.size();

    }


}
