package gameMap;

import org.junit.Test;
import player.Player;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class GameMapTest {

    private GameMap createDefaultSizedGameMap() {
        return new GameMap();
    }

    private GameMap createCustomSizedGameMap(int xLength, int yLength) {
        return new GameMap(xLength, yLength);
    }

    private GameMap createGameMapAndPlacePlayer(int x, int y) {

        Player player = mock(Player.class);

        GameMap gameMap = createDefaultSizedGameMap();
        gameMap.place(player, new Point(x, y));

        return gameMap;
    }

    @Test
    public void constructGameMapWithEmptyConstructor() {

        GameMap gameMap = createDefaultSizedGameMap();

        assertEquals(GameMap.DEFAULT_X_LENGTH, gameMap.getXLength());
        assertEquals(GameMap.DEFAULT_Y_LENGTH, gameMap.getYLength());

    }

    @Test
    public void constructGameMapWithValidArgumentsToConstructor() {

        GameMap gameMap = createCustomSizedGameMap(20, 20);

        assertEquals(20, gameMap.getXLength());
        assertEquals(20, gameMap.getYLength());

    }

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

    @Test
    public void getPlayerPosition() {

        Player player = mock(Player.class);

        GameMap gameMap = createDefaultSizedGameMap();

        gameMap.place(player, new Point(0, 0));

        assertEquals(new Point(0, 0), gameMap.getPosition(player));

    }

    @Test
    public void movePlayerNorth() {

        Player player = mock(Player.class);

        GameMap gameMap = createDefaultSizedGameMap();

        gameMap.place(player, new Point(0, 0));

        assertTrue(gameMap.move(player, GameMap.NORTH));
        assertEquals(gameMap.getPosition(player), new Point(0, 1));

    }

    @Test
    public void movePlayerSouth() {

        Player player = mock(Player.class);

        GameMap gameMap = createDefaultSizedGameMap();

        gameMap.place(player, new Point(0, 1));

        assertTrue(gameMap.move(player, GameMap.SOUTH));
        assertEquals(gameMap.getPosition(player), new Point(0, 0));

    }

    @Test
    public void movePlayerWest() {

        Player player = mock(Player.class);

        GameMap gameMap = createDefaultSizedGameMap();

        gameMap.place(player, new Point(0, 0));

        assertTrue(gameMap.move(player, GameMap.WEST));
        assertEquals(gameMap.getPosition(player), new Point(1, 0));

    }
}
