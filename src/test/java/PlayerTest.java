import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PlayerTest {

    Player player = new Player("Maja");

    @Test
    public void createPlayerWithDefaultValues(){
        assertEquals("Maja", player.getName());
        assertEquals(5, player.getLives());
    }

    @Test
    public void testIncreaseLives(){
        player.addLife();
        assertEquals(6, player.getLives());
    }

    @Test
    public void addSeveralLives(){
        int lives = player.getLives();
        for(int i = 1; i < 5; i++){
            player.addLife();
            assertEquals(lives + i, player.getLives());
        }
    }

    @Test
    public void testDefaultSpeedWhenCreatingNewPlayer(){
        Player p1 = new Player("Henrik");
        assertEquals(100, player.getSpeed());
        assertEquals(100, player.getSpeed());
    }

    @Test
    public void testIncreaseSpeed(){
        player.increaseSpeed(10);
        assertEquals(110, player.getSpeed());
    }
}
