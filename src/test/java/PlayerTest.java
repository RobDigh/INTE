
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerTest {

	@Test
	public void createNoArgPlayerTest(){
		Player p1 = new Player();
		
		p1.setName("Dungeooni");
		assertEquals("Dungeooni", p1.getName());
		assertEquals(100, p1.getHealthPoint());
	}
	
}
