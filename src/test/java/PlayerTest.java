
import org.junit.Test;
import org.junit.Assert;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerTest {

	@Test
	void createNoArgPlayerTest(){
		Player p1 = new Player();
		assertEqual();
	}
	
}
