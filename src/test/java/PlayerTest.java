import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PlayerTest {

    @Test
    public void createPlayerWithDefaultValues(){
        Player player = new Player("Maja");
        assertEquals("Maja", player.getName());
        assertEquals(5, player.getLives());
    }

    @Test
    public void increaseLives(){
        Player player = new Player("Maja");
        player.addLife();
        assertEquals(6, player.getLives());
    }
}
