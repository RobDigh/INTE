package gameMap;

import org.junit.Test;
import player.Player;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class PlayerPlacementTest extends GameMapTest {

    @Test
    public void placePlayer() {

        Player player = mock(Player.class);

        GameMap gameMap = createDefaultSizedGameMap();

        assertTrue(gameMap.place(player, new Point(0, 0)));

    }

    @Test(expected = IllegalArgumentException.class)
    public void placePlayerAtNegativeX() {
        createGameMapAndPlacePlayer(-1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placePlayerAtNegativeY() {
        createGameMapAndPlacePlayer(0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placePlayerAtTooGreatX() {
        createGameMapAndPlacePlayer(GameMap.DEFAULT_X_LENGTH, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placePlayerAtTooGreatY() {
        createGameMapAndPlacePlayer(0, GameMap.DEFAULT_Y_LENGTH);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placePlayerAtTooGreatXOnCustomSizedMap() {
        placePlayer(createCustomSizedGameMap(5, 5), 5, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placePlayerAtTooGreatYOnCustomSizedMap() {
        placePlayer(createCustomSizedGameMap(5, 5), 0, 5);
    }

    @Test
    public void getPlayerPosition() {

        Player player = mock(Player.class);

        GameMap gameMap = createDefaultSizedGameMap();

        gameMap.place(player, new Point(0, 0));

        assertEquals(new Point(0, 0), gameMap.getPosition(player));

    }
}
