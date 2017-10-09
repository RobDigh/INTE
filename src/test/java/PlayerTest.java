
import org.junit.Test;

import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;

public class PlayerTest {

	@Test
	public void createNoArgPlayerTest(){
		Player p1 = new Player();
		
		p1.setName("Dungeooni");
		assertEquals("Dungeooni", p1.getName());

	}
	
	public void createPlayerWithArgs(){
		Player p1 = new Player(1,1,"Advokaat",1,10, true);
		assertEquals(10, p1.getGold());
	}
	
	public void playerIsAliveOrDead(){
		Player p1 = new Player(1,1,"Dietrich",1,10, true);
		p1.setHealthPoint(0);
		assertEquals(true, p1.isAlive());
	}
}
