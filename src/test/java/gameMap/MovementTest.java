package gameMap;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class MovementTest extends GameMapTest {

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

        assertTrue(placeAndMovePlayer(DEFAULT_SIZED_MAP, 0, 2, GameMap.SOUTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(0, 0));

    }

    @Test
    public void moveHighSpeedPlayerSouthFromRiskyPosition() {

        /*
         * This test was added to catch a bug resulting from:
         * int toSubtract = Math.max(0, Math.abs(playerPosition.y - player.getSpeed()));
         */

        when(mockPlayer.getSpeed()).thenReturn(2);

        assertTrue(placeAndMovePlayer(DEFAULT_SIZED_MAP, 0, 4, GameMap.SOUTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(0, 2));

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
    public void moveHighSpeedPlayerWest() {

        when(mockPlayer.getSpeed()).thenReturn(2);

        assertTrue(placeAndMovePlayer(DEFAULT_SIZED_MAP, 0, 0, GameMap.WEST));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(2, 0));

    }

    @Test
    public void moveHighSpeedPlayerWestOverEdge() {

        when(mockPlayer.getSpeed()).thenReturn(2);

        assertFalse(placeAndMovePlayer(DEFAULT_SIZED_MAP, GameMap.DEFAULT_X_LENGTH - 1, 0, GameMap.WEST));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(GameMap.DEFAULT_X_LENGTH - 1, 0));

    }

    @Test
    public void moveHighSpeedPlayerWestOverEdgeOnCustomSizedMap() {

        when(mockPlayer.getSpeed()).thenReturn(2);

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertFalse(placeAndMovePlayer(gameMap, 4, 0, GameMap.WEST));
        assertEquals(gameMap.getPosition(mockPlayer), new Point(4,0));

    }

    @Test
    public void moveHighSpeedPlayerWestToEdge() {

        when(mockPlayer.getSpeed()).thenReturn(2);

        assertTrue(placeAndMovePlayer(DEFAULT_SIZED_MAP,  GameMap.DEFAULT_X_LENGTH - 2, 0, GameMap.WEST));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(GameMap.DEFAULT_X_LENGTH - 1, 0));

    }

    @Test
    public void moveHighSpeedPlayerWestToEdgeOnCustomSizedMap() {

        when(mockPlayer.getSpeed()).thenReturn(2);

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertTrue(placeAndMovePlayer(gameMap, 3, 0, GameMap.WEST));
        assertEquals(new Point(4, 0), gameMap.getPosition(mockPlayer));

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

    @Test
    public void moveHighSpeedPlayerEast() {

        when(mockPlayer.getSpeed()).thenReturn(2);

        assertTrue(placeAndMovePlayer(DEFAULT_SIZED_MAP, 2, 0, GameMap.EAST));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(0, 0));

    }

    @Test
    public void moveHighSpeedPlayerEastFromRiskyPosition() {

        when(mockPlayer.getSpeed()).thenReturn(2);

        assertTrue(placeAndMovePlayer(DEFAULT_SIZED_MAP, 4, 0, GameMap.EAST));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(2, 0));

    }

    @Test
    public void moveHighSpeedPlayerEastOverEdge() {

        when(mockPlayer.getSpeed()).thenReturn(2);

        assertFalse(placeAndMovePlayer(DEFAULT_SIZED_MAP, 0, 0, GameMap.EAST));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(0,0));

    }

    @Test
    public void moveHighSpeedPlayerEastToEdge() {

        when(mockPlayer.getSpeed()).thenReturn(2);

        assertTrue(placeAndMovePlayer(DEFAULT_SIZED_MAP, 1, 0, GameMap.EAST));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(0, 0));

    }

    @Test
    public void moveMonsterNorth() {

        assertTrue(placeAndMoveMonster(DEFAULT_SIZED_MAP, 0, 0, GameMap.NORTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(0, 1));

    }
}
