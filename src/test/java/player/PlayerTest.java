package player;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    private Player createPlayerWithCustomHPAndSpeed(int hp, int speed) {
        return new Player(hp, speed);
    }

    @Test
    public void testConstructPlayerWithValidHPAndSpeed() {

        Player player = createPlayerWithCustomHPAndSpeed(100, 1);

        assertEquals(100, player.getHP());
        assertEquals(1, player.getSpeed());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructPlayerWithNegativeHP() {
        createPlayerWithCustomHPAndSpeed(-1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructPlayerWithZeroHP() {
        createPlayerWithCustomHPAndSpeed(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructPlayerWithNegativeSpeed() {
        createPlayerWithCustomHPAndSpeed(100, -1);
    }
}
