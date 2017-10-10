package entity;

import entity.Player;
import gameMap.GameMap;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlayerTest {

    private GameMap mockGameMap = mock(GameMap.class);

    private Player createPlayerWithCustomHPAndSpeed(int hp, int speed) {
        return new Player(hp, speed, mockGameMap);
    }

    private Player createSamplePlayer() {
        return createPlayerWithCustomHPAndSpeed(100, 1);
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

    @Test(expected = IllegalArgumentException.class)
    public void testConstructPlayerWithZeroSpeed() {
        createPlayerWithCustomHPAndSpeed(100,0);
    }

    @Test
    public void testGetHP() {

        Player player = createPlayerWithCustomHPAndSpeed(500, 10);
        assertEquals(500, player.getHP());

    }

    @Test
    public void testGetSpeed() {

        Player player = createPlayerWithCustomHPAndSpeed(500, 10);
        assertEquals(10, player.getSpeed());

    }

    @Test
    public void testMove() {

        Player player = createSamplePlayer();
        player.move(0, 1);

        verify(mockGameMap).move(player, GameMap.NORTH);

    }
}
