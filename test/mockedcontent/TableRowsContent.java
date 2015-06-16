package mockedcontent;

import java.util.ArrayList;
import java.util.List;

public class TableRowsContent {

	public static List<String> getRows() {
		List<String> rows = new ArrayList<String>();
		rows.addAll(createRow("$10.20", "jack", "my location", "76"));
		rows.addAll(createRow("$5.30", "mary", "my location2", "158"));
		rows.addAll(createRow("$100.00", "david", "A very long long long location", "4"));
		rows.addAll(createRow("$1.2", "mary", "sarah new location", "23"));
		return rows;

	}

	private static List<String> createRow(String rate, String name, String location, String size) {
		List<String> row = new ArrayList<String>();
		// row.add("0");

		row.add(name);
		row.add(location);
		row.add(size);
		row.add(rate);
		return row;
	}

}
