import org.junit.Before;
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
    public void increaseLives(){
        player.addLife();
        assertEquals(6, player.getLives());
    }

    @Test
    public void addThreeLives(){
        int lives = player.getLives();
        for(int i = 1; i < 5; i++){
            player.addLife();
            assertEquals(lives + i, player.getLives());
        }
    }
}
