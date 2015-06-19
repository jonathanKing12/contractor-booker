package mockedcontent;

import java.util.ArrayList;
import java.util.List;

public class TableRowsContent {

	public static List<String> getNameColumn() {//32
		return createColumn( "jack", "mary", "david","mary");
	}
	
	public static List<String> geLocationColumn() {//64
		return createColumn( "my location", "my location2", "A very long long long location","sarah new location");
	}
	
	public static List<String> getHourlyRateColumn() {//8
		return createColumn( "$10.20", "$5.30", "$100.00","$1.2");
	}
	
	public static List<String> getSizeColumn() {//6
		return createColumn( "76", "158", "4","23");
	}

	private static List<String> createColumn(String item1, String item2, String item3, String item4) {
		List<String> column = new ArrayList<String>();
		column.add(item1);
		column.add(item2);
		column.add(item3);
		column.add(item4);
		return column;
	}
	

}
