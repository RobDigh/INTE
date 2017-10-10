
import org.junit.Test;

import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;

public class PlayerTest {
	
	@Test
	public void createNoArgPlayerTest(){
		
		//given
		Player sut = new Player();
		//when
		sut.setName("Dungeooni");
		//then
		assertEquals("Dungeooni", sut.getName());

	}
	
	@Test
	public void createPlayerWithFullArgs(){
		//given
		Player sut = new Player(1, 1, "Advokaat", 1, 100, 1, 1, true);
		//when
		sut.setGold(10);
		//then
		assertEquals(10, sut.getGold());
	}
	
	@Test
	public void decreaseHealthAttribute(){
		//given
		Player sut = new Player(1,1,"Germaine",1);
		//when
		sut.decreasePlayerHealth(50);
		//then
		assertEquals(50, sut.getHealthPoint());
	}
	
	@Test
	public void decreaseHealthAttributeToNegative(){
		//given
		Player sut = new Player(1,1,"Tourmaline",1);
		//when
		sut.decreasePlayerHealth(150);
		//then
		assertEquals(0, sut.getHealthPoint());
	}
	
	@Test
	public void decreaseSpeedTest(){
		//given
		Player sut = new Player(1,1,"Scarabite",10);
		//when
		sut.decreasePlayerSpeed(10);
		//then
		assertEquals(1, sut.getSpeedPoint());
	}
	
	@Test
	public void decreaseSpeedToNegativeTest(){
		//given
		Player sut = new Player(1,1,"Amethyst",10);
		//when
		sut.decreasePlayerSpeed(100);
		//then
		assertEquals(1, sut.getSpeedPoint());
	}
	 
	@Test
	public void increasePlayerGoldAmmount(){
		//given
		Player sut = new Player(1,1,"Topaz",1);
		//when
		sut.increasePlayerGold(100);
		//then
		assertEquals(100, sut.getGold());
	}
	
	@Test
	public void decreasePlayerGoldAmmount(){
		//given
		Player sut = new Player(1,1,"Emerald",1);
		//when
		sut.increasePlayerGold(100);
		sut.decreasePlayerGold();
		//then
		assertEquals(25, sut.getGold());
	}
	
	@Test
	public void playerIsAliveOrDead(){
		//given
		Player sut = new Player(1, 1, "Dietrich", 1);
		//when
		sut.setHealthPoint(0);
		sut.checkHealthStatus();
		//then
		assertEquals(false, sut.isAlive());
	}

}
