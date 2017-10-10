import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;

public class BoardTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void createDefaultBoardNoArgumentsTest(){
		//given
		Board sut = new Board();
		//when
		sut.setDefaultBoardValues();
		//then
		assertEquals(10, sut.getBoardValueX());
		assertEquals(10, sut.getBoardValueY());
	}
	
	@Test
	public void createCustomBoardSizeTest(){
		//given
		Board sut = new Board();
		//when 
		sut.createCustomBoard(20,20);
		//then
		assertEquals(20, sut.getBoardValueX());
		assertEquals(20, sut.getBoardValueY());
	}
	
	@Test
	public void createCustomBoardSizeWithNegativeValue(){
		//given
		Board sut = new Board();
		//when
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Board X/Y values can't be negative!");
		//then
		sut.createCustomBoard(-10, 10);
	}
	
	@Test
	public void minimumCustomBoardSizeTest(){
		//given
		Board sut = new Board();
		//when
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Board X/Y values can't be less than 10");
		//then
		sut.createCustomBoard(15, 7);
	}
}
