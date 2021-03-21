import java.util.ArrayList;

public class Model {
	private DataFrame dataFrame;
    ArrayList<String> selectedColumns = new  ArrayList<String>();
	public Model () {
		dataFrame = new DataFrame();
	}
	public String loadData(String fileName) {
		dataFrame = DataLoader.loadData(fileName);
		for (String c: getAllColumns()) {
			selectedColumns.add(c);
		}
		if (dataFrame != null) {
			return dataFrame.toString();
		} else {
			return "File not found";
		}

		
	}
	
	
	
	public String getDataFrameValues() {
		return dataFrame.toString(selectedColumns);
	}
	
	public ArrayList<String> getAllColumns() {
		return dataFrame.getColumnNames();
	}
	
	public ArrayList<String> getSelectedColumns() {
		return selectedColumns;
	}
	
	public void remove(String s) {
		selectedColumns.remove(s);
	}
	
	public void add(String s) {
		selectedColumns.add(s);
	}
	
	
}
