package suncertify.db.table;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TableCellPointerTest {
	
	private static final int NUMBR_OF_CELLS_IN_ROW =7;
	private static final int FIFTH_CELL_POSITION = 4;
	private static final int SECOND_ROW_NUMBER = 1;
	private static final int FIRST_ROW_NUMBER = 0;
	private static final int FIRST_CELL_NUMBER = 0;
	
	private TableCellPointer cellPointer;
	
	@Before
	public void setUp(){
		cellPointer=new TableCellPointer();
	}
	
	@Test
	public void shouldBeAtFirstCell(){
		cellPointer.setNumberOfColumns(NUMBR_OF_CELLS_IN_ROW);
		int actualColumn=cellPointer.getColumnNumber();
		assertEquals(FIRST_CELL_NUMBER,actualColumn);
	}
	
	@Test
	public void shouldBeAtFirstRow(){
		cellPointer.setNumberOfColumns(NUMBR_OF_CELLS_IN_ROW);
		int actualRowNumber=cellPointer.getRowNumber();
		assertEquals(FIRST_ROW_NUMBER,actualRowNumber);
	}
	
	@Test
	public void shouldMoveToStartOfSecondRow(){
		cellPointer.setNumberOfColumns(NUMBR_OF_CELLS_IN_ROW);
		cellPointer.moveToStartOfRow(SECOND_ROW_NUMBER);
		
		int actualRowNumber=cellPointer.getRowNumber();
		int actualColumn=cellPointer.getColumnNumber();
		
		assertEquals(SECOND_ROW_NUMBER,actualRowNumber);
		assertEquals(FIRST_CELL_NUMBER,actualColumn);
	}
	
	@Test
	public void shouldMoveToFiftCell(){
		cellPointer.setNumberOfColumns(NUMBR_OF_CELLS_IN_ROW);
	
		for(int i=0;i<4;i++)
			cellPointer.moveToNextColumn();
		
		int actualColumn=cellPointer.getColumnNumber();		
		assertEquals(FIFTH_CELL_POSITION,actualColumn);
	}
	
	@Test
	public void shouldMoveToStartOfNextRowIfReachedEndOfRow(){
		cellPointer.setNumberOfColumns(NUMBR_OF_CELLS_IN_ROW);
	
		for(int i=0;i<NUMBR_OF_CELLS_IN_ROW;i++)
			cellPointer.moveToNextColumn();
		
		int actualColumn=cellPointer.getColumnNumber();	
		int actualRowNumber=cellPointer.getRowNumber();
		
		assertEquals(FIRST_CELL_NUMBER,actualColumn);
		assertEquals(SECOND_ROW_NUMBER,actualRowNumber);
	}

}
