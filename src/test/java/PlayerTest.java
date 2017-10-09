import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PlayerTest {

    @Test
    public void createPlayerWithDefaultValues(){
        Player player = new Player("Maja");
        assertEquals("Maja", player.getName());
    }

}
