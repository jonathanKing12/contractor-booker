package mockedcontent;

import java.util.ArrayList;
import java.util.List;

public class TableHeaderContent {

	private static final int IS_FILE_COOOKIE = 1;
	private static final int OFFSET_TO_FIRST_ROW = 20;
	private static final short TOTAL_NUMBER_OF_COLUMN = 4;
	private static final short COLUMN_2_NAME_SIZE = 2;
	private static final short COLUMN_3_NAME_SIZE = 3;
	private static final short COLUMN_4_NAME_SIZE = 5;
	private static final short COLUMN_5_NAME_SIZE = 7;
	private static final short COLUMN_2_VALUE_SIZE = 6;
	private static final short COLUMN_3_VALUE_SIZE = 32;
	private static final short COLUMN_4_VALUE_SIZE = 64;
	private static final short COLUMN_5_VALUE_SIZE = 8;

	public static List<Integer> getInts() {
		List<Integer> dataInts = new ArrayList<>();
		dataInts.add(IS_FILE_COOOKIE);
		dataInts.add(OFFSET_TO_FIRST_ROW);
		return dataInts;
	}

	public static List<Short> getShorts() {
		List<Short> dataShorts = new ArrayList<>();
		dataShorts.add(TOTAL_NUMBER_OF_COLUMN);
		dataShorts.add(COLUMN_2_NAME_SIZE);
		dataShorts.add(COLUMN_2_VALUE_SIZE);
		dataShorts.add(COLUMN_3_NAME_SIZE);
		dataShorts.add(COLUMN_3_VALUE_SIZE);
		dataShorts.add(COLUMN_4_NAME_SIZE);
		dataShorts.add(COLUMN_4_VALUE_SIZE);
		dataShorts.add(COLUMN_5_NAME_SIZE);
		dataShorts.add(COLUMN_5_VALUE_SIZE);
		return dataShorts;
	}

	public static List<Integer> getMoves() {
		List<Integer> moves = new ArrayList<>();
		moves.add((int) COLUMN_2_NAME_SIZE);
		moves.add((int) COLUMN_3_NAME_SIZE);
		moves.add((int) COLUMN_4_NAME_SIZE);
		moves.add((int) COLUMN_5_NAME_SIZE);
		return moves;
	}

}
