import org.junit.Test;
import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;

public class BoardTest {

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
}
