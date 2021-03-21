import java.util.ArrayList;

public class DataFrame {
	private ArrayList<Column> columns;

	public DataFrame () {
		columns = new ArrayList<Column>();
	}

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

	public int getRowCount () {
		if (!columns.isEmpty()) {
			return columns.get(0).getSize();
		} else {
			return -1;
		}
	}

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

	public void addValue(String columnName, String value) {
		for (Column c : columns) {
			if (columnName.equals(c.getName())) {
				c.addRowValue(value);
			}
		}
	}

	public String toString() {
		String result="";
		for (Column c: columns) {
			result+=c.getName() + "\t";
		}
		result+="\n";
		for (int row=0; row<getRowCount(); row++) {
			for (Column c: columns) {
				try {
				result+=getValue(c.getName(),row) + "\t";
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			result+="\n";
		}
		result+="\n";
		
		return result;
		
		
	}
	
	public String toString(ArrayList<String> selectedColumns) {
		String result="";
		for (int row=0; row<getRowCount(); row++) {
			for (Column c: columns) {
				if (selectedColumns.contains(c.getName()))
				try {
				result+=getValue(c.getName(),row) + "\t";
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			result+="\n";
		}
		result+="\n";
		
		return result;
		
		
	}


}
