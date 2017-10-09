package player;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    @Test
    public void testConstructPlayerWithValidHPAndSpeed() {

        Player player = new Player(100, 1);

        assertEquals(100, player.getHP());
        assertEquals(1, player.getSpeed());

    }
}