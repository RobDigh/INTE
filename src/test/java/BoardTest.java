import org.junit.Test;
import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;

public class BoardTest {

	@Test
	public void createDefaultBoardNoArgumentsTest(){
		//given
		Board sut = new Board();
		//then
		assertEquals(Board.DEFAULT_BOARD_HEIGHT, sut.DEFAULT_BOARD_HEIGHT);
		assertEquals(Board.DEFAULT_BOARD_LENGTH, sut.DEFAULT_BOARD_LENGTH);
	}
	
}
