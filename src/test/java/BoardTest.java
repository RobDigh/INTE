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
		sut.setDefaultBoardValueX();
		sut.setDefaultBoardValueY();
		//then
		assertEquals(Board.DEFAULT_BOARD_HEIGHT, sut.getBoardValueX());
		assertEquals(Board.DEFAULT_BOARD_LENGTH, sut.getBoardValueY());
	}
	
}
