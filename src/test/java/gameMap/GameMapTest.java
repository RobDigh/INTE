package gameMap;

import org.junit.Before;
import org.junit.Test;
import player.Player;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameMapTest {

    private final GameMap DEFAULT_SIZED_MAP = createDefaultSizedGameMap();
    private Player mockPlayer = mock(Player.class);

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

    private boolean placeAndMovePlayer(GameMap gameMap, int x, int y, int direction) {

        gameMap.place(mockPlayer, new Point(x, y));
        return gameMap.move(mockPlayer, direction);

    }

    @Before
    public void setup() {
        when(mockPlayer.getSpeed()).thenReturn(1);
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

        assertTrue(placeAndMovePlayer(DEFAULT_SIZED_MAP, 0, 0, GameMap.NORTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(0, 1));

    }

    @Test
    public void movePlayerNorthOverEdge() {

        assertFalse(placeAndMovePlayer(DEFAULT_SIZED_MAP, 0, GameMap.DEFAULT_Y_LENGTH - 1, GameMap.NORTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(0,GameMap.DEFAULT_Y_LENGTH - 1));

    }

    @Test
    public void movePlayerNorthOverEdgeOnCustomSizedMap() {

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertFalse(placeAndMovePlayer(gameMap, 0, 4, GameMap.NORTH));
        assertEquals(gameMap.getPosition(mockPlayer), new Point(0,4));

    }

    @Test
    public void moveHighSpeedPlayerNorth() {

        when(mockPlayer.getSpeed()).thenReturn(2);

        assertTrue(placeAndMovePlayer(DEFAULT_SIZED_MAP, 0, 0, GameMap.NORTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(0, 2));

    }

    @Test
    public void moveHighSpeedPlayerNorthOverEdge() {

        when(mockPlayer.getSpeed()).thenReturn(2);

        assertFalse(placeAndMovePlayer(DEFAULT_SIZED_MAP, 0, GameMap.DEFAULT_Y_LENGTH - 1, GameMap.NORTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(0,GameMap.DEFAULT_Y_LENGTH - 1));

    }

    @Test
    public void moveHighSpeedPlayerNorthOverEdgeOnCustomSizedMap() {

        when(mockPlayer.getSpeed()).thenReturn(2);

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertFalse(placeAndMovePlayer(gameMap, 0, 4, GameMap.NORTH));
        assertEquals(gameMap.getPosition(mockPlayer), new Point(0,4));

    }

    @Test
    public void moveHighSpeedPlayerNorthToEdge() {

        when(mockPlayer.getSpeed()).thenReturn(2);

        assertTrue(placeAndMovePlayer(DEFAULT_SIZED_MAP, 0, GameMap.DEFAULT_Y_LENGTH - 2, GameMap.NORTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(0,GameMap.DEFAULT_Y_LENGTH - 1));

    }

    @Test
    public void moveHighSpeedPlayerNorthToEdgeOnCustomSizedMap() {

        when(mockPlayer.getSpeed()).thenReturn(2);

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertTrue(placeAndMovePlayer(gameMap, 0, 3, GameMap.NORTH));
        assertEquals(new Point(0, 4), gameMap.getPosition(mockPlayer));

    }

    @Test
    public void movePlayerSouth() {

        assertTrue(placeAndMovePlayer(DEFAULT_SIZED_MAP, 0, 1, GameMap.SOUTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(0, 0));

    }

    @Test
    public void movePlayerOverSouthEdge() {

        assertFalse(placeAndMovePlayer(DEFAULT_SIZED_MAP, 0,0, GameMap.SOUTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(0, 0));

    }

    @Test
    public void moveHighSpeedPlayerSouth() {

        when(mockPlayer.getSpeed()).thenReturn(2);

        assertTrue(placeAndMovePlayer(DEFAULT_SIZED_MAP, 0, 1, GameMap.SOUTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(0, 0));

    }

    @Test
    public void moveHighSpeedPlayerSouthOverEdge() {

        when(mockPlayer.getSpeed()).thenReturn(2);

        assertFalse(placeAndMovePlayer(DEFAULT_SIZED_MAP, 0, 0, GameMap.SOUTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(0,0));

    }

    @Test
    public void moveHighSpeedPlayerSouthToEdge() {

        when(mockPlayer.getSpeed()).thenReturn(2);

        assertTrue(placeAndMovePlayer(DEFAULT_SIZED_MAP, 0, 1, GameMap.SOUTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(0, 0));

    }

    @Test
    public void movePlayerWest() {

        assertTrue(placeAndMovePlayer(DEFAULT_SIZED_MAP, 0,0, GameMap.WEST));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(1, 0));

    }

    @Test
    public void movePlayerWestOverEdge() {

        assertFalse(placeAndMovePlayer(DEFAULT_SIZED_MAP, GameMap.DEFAULT_X_LENGTH - 1,0, GameMap.WEST));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(GameMap.DEFAULT_X_LENGTH - 1, 0));

    }

    @Test
    public void movePlayerWestOverEdgeOnCustomSizedMap() {

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertFalse(placeAndMovePlayer(gameMap, 4,0, GameMap.WEST));
        assertEquals(gameMap.getPosition(mockPlayer), new Point(4, 0));

    }

    @Test
    public void movePlayerEast() {

        assertTrue(placeAndMovePlayer(DEFAULT_SIZED_MAP, 1,0, GameMap.EAST));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(0, 0));

    }

    @Test
    public void movePlayerEastOverEdge() {

        assertFalse(placeAndMovePlayer(DEFAULT_SIZED_MAP, 0,0, GameMap.EAST));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(0, 0));

    }
}
