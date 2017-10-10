package gameMap;

import combat.Combat;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovementTest extends GameMapTest {

    /*
     * Player movement
     */

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

    /*
     * Monster movement
     */

    @Test
    public void moveMonsterNorth() {

        assertTrue(placeAndMoveMonster(DEFAULT_SIZED_MAP, 0, 0, GameMap.NORTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(0, 1));

    }

    @Test
    public void moveMonsterNorthOverEdge() {

        assertFalse(placeAndMoveMonster(DEFAULT_SIZED_MAP, 0, GameMap.DEFAULT_Y_LENGTH - 1, GameMap.NORTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(0,GameMap.DEFAULT_Y_LENGTH - 1));

    }

    @Test
    public void moveMonsterNorthOverEdgeOnCustomSizedMap() {

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertFalse(placeAndMoveMonster(gameMap, 0, 4, GameMap.NORTH));
        assertEquals(gameMap.getPosition(mockMonster), new Point(0,4));

    }

    @Test
    public void moveHighSpeedMonsterNorth() {

        when(mockMonster.getSpeed()).thenReturn(2);

        assertTrue(placeAndMoveMonster(DEFAULT_SIZED_MAP, 0, 0, GameMap.NORTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(0, 2));

    }

    @Test
    public void moveHighSpeedMonsterNorthOverEdge() {

        when(mockMonster.getSpeed()).thenReturn(2);

        assertFalse(placeAndMoveMonster(DEFAULT_SIZED_MAP, 0, GameMap.DEFAULT_Y_LENGTH - 1, GameMap.NORTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(0,GameMap.DEFAULT_Y_LENGTH - 1));

    }

    @Test
    public void moveHighSpeedMonsterNorthOverEdgeOnCustomSizedMap() {

        when(mockMonster.getSpeed()).thenReturn(2);

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertFalse(placeAndMoveMonster(gameMap, 0, 4, GameMap.NORTH));
        assertEquals(gameMap.getPosition(mockMonster), new Point(0,4));

    }

    @Test
    public void moveHighSpeedMonsterNorthToEdge() {

        when(mockMonster.getSpeed()).thenReturn(2);

        assertTrue(placeAndMoveMonster(DEFAULT_SIZED_MAP, 0, GameMap.DEFAULT_Y_LENGTH - 2, GameMap.NORTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(0,GameMap.DEFAULT_Y_LENGTH - 1));

    }

    @Test
    public void moveHighSpeedMonsterNorthToEdgeOnCustomSizedMap() {

        when(mockMonster.getSpeed()).thenReturn(2);

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertTrue(placeAndMoveMonster(gameMap, 0, 3, GameMap.NORTH));
        assertEquals(new Point(0, 4), gameMap.getPosition(mockMonster));

    }

    @Test
    public void moveMonsterSouth() {

        assertTrue(placeAndMoveMonster(DEFAULT_SIZED_MAP, 0, 1, GameMap.SOUTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(0, 0));

    }

    @Test
    public void moveMonsterOverSouthEdge() {

        assertFalse(placeAndMoveMonster(DEFAULT_SIZED_MAP, 0,0, GameMap.SOUTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(0, 0));

    }

    @Test
    public void moveHighSpeedMonsterSouth() {

        when(mockMonster.getSpeed()).thenReturn(2);

        assertTrue(placeAndMoveMonster(DEFAULT_SIZED_MAP, 0, 2, GameMap.SOUTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(0, 0));

    }

    @Test
    public void moveHighSpeedMonsterSouthFromRiskyPosition() {

        /*
         * This test was added to catch a bug resulting from:
         * int toSubtract = Math.max(0, Math.abs(playerPosition.y - player.getSpeed()));
         */

        when(mockMonster.getSpeed()).thenReturn(2);

        assertTrue(placeAndMoveMonster(DEFAULT_SIZED_MAP, 0, 4, GameMap.SOUTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(0, 2));

    }

    @Test
    public void moveHighSpeedMonsterSouthOverEdge() {

        when(mockMonster.getSpeed()).thenReturn(2);

        assertFalse(placeAndMoveMonster(DEFAULT_SIZED_MAP, 0, 0, GameMap.SOUTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(0,0));

    }

    @Test
    public void moveHighSpeedMonsterSouthToEdge() {

        when(mockMonster.getSpeed()).thenReturn(2);

        assertTrue(placeAndMoveMonster(DEFAULT_SIZED_MAP, 0, 1, GameMap.SOUTH));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(0, 0));

    }

    @Test
    public void moveMonsterWest() {

        assertTrue(placeAndMoveMonster(DEFAULT_SIZED_MAP, 0,0, GameMap.WEST));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(1, 0));

    }

    @Test
    public void moveMonsterWestOverEdge() {

        assertFalse(placeAndMoveMonster(DEFAULT_SIZED_MAP, GameMap.DEFAULT_X_LENGTH - 1,0, GameMap.WEST));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(GameMap.DEFAULT_X_LENGTH - 1, 0));

    }

    @Test
    public void moveMonsterWestOverEdgeOnCustomSizedMap() {

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertFalse(placeAndMoveMonster(gameMap, 4,0, GameMap.WEST));
        assertEquals(gameMap.getPosition(mockMonster), new Point(4, 0));

    }

    @Test
    public void moveHighSpeedMonsterWest() {

        when(mockMonster.getSpeed()).thenReturn(2);

        assertTrue(placeAndMoveMonster(DEFAULT_SIZED_MAP, 0, 0, GameMap.WEST));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(2, 0));

    }

    @Test
    public void moveHighSpeedMonsterWestOverEdge() {

        when(mockMonster.getSpeed()).thenReturn(2);

        assertFalse(placeAndMoveMonster(DEFAULT_SIZED_MAP, GameMap.DEFAULT_X_LENGTH - 1, 0, GameMap.WEST));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(GameMap.DEFAULT_X_LENGTH - 1, 0));

    }

    @Test
    public void moveHighSpeedMonsterWestOverEdgeOnCustomSizedMap() {

        when(mockMonster.getSpeed()).thenReturn(2);

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertFalse(placeAndMoveMonster(gameMap, 4, 0, GameMap.WEST));
        assertEquals(gameMap.getPosition(mockMonster), new Point(4,0));

    }

    @Test
    public void moveHighSpeedMonsterWestToEdge() {

        when(mockMonster.getSpeed()).thenReturn(2);

        assertTrue(placeAndMoveMonster(DEFAULT_SIZED_MAP,  GameMap.DEFAULT_X_LENGTH - 2, 0, GameMap.WEST));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(GameMap.DEFAULT_X_LENGTH - 1, 0));

    }

    @Test
    public void moveHighSpeedMonsterWestToEdgeOnCustomSizedMap() {

        when(mockMonster.getSpeed()).thenReturn(2);

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertTrue(placeAndMoveMonster(gameMap, 3, 0, GameMap.WEST));
        assertEquals(new Point(4, 0), gameMap.getPosition(mockMonster));

    }

    @Test
    public void moveMonsterEast() {

        assertTrue(placeAndMoveMonster(DEFAULT_SIZED_MAP, 1,0, GameMap.EAST));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(0, 0));

    }

    @Test
    public void moveMonsterEastOverEdge() {

        assertFalse(placeAndMoveMonster(DEFAULT_SIZED_MAP, 0,0, GameMap.EAST));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(0, 0));

    }

    @Test
    public void moveHighSpeedMonsterEast() {

        when(mockMonster.getSpeed()).thenReturn(2);

        assertTrue(placeAndMoveMonster(DEFAULT_SIZED_MAP, 2, 0, GameMap.EAST));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(0, 0));

    }

    @Test
    public void moveHighSpeedMonsterEastFromRiskyPosition() {

        when(mockMonster.getSpeed()).thenReturn(2);

        assertTrue(placeAndMoveMonster(DEFAULT_SIZED_MAP, 4, 0, GameMap.EAST));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(2, 0));

    }

    @Test
    public void moveHighSpeedMonsterEastOverEdge() {

        when(mockMonster.getSpeed()).thenReturn(2);

        assertFalse(placeAndMoveMonster(DEFAULT_SIZED_MAP, 0, 0, GameMap.EAST));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(0,0));

    }

    @Test
    public void moveHighSpeedMonsterEastToEdge() {

        when(mockMonster.getSpeed()).thenReturn(2);

        assertTrue(placeAndMoveMonster(DEFAULT_SIZED_MAP, 1, 0, GameMap.EAST));
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(0, 0));

    }

    /*
     * Collision
     */

    @Test
    public void movePlayerOntoMonsterAndWinCombat() {

        when(mockMonster.visit(mockPlayer, DEFAULT_SIZED_MAP))
                .then(invocationOnMock -> {
                    DEFAULT_SIZED_MAP.removeMonster(mockMonster);
                    return Combat.INITIATOR_WIN;
                });

        DEFAULT_SIZED_MAP.place(mockMonster, new Point(0, 1));

        assertTrue(placeAndMovePlayer(DEFAULT_SIZED_MAP, 0, 0, GameMap.NORTH));

        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(0, 1));
        assertEquals(null, DEFAULT_SIZED_MAP.getPosition(mockMonster));

    }

    @Test
    public void movePlayerOntoMonsterAndLoseCombat() {

        when(mockMonster.visit(mockPlayer, DEFAULT_SIZED_MAP)).then(invocationOnMock -> {
            DEFAULT_SIZED_MAP.removePlayer(mockPlayer);
            return Combat.INITIATOR_LOSS;
        });

        DEFAULT_SIZED_MAP.place(mockMonster, new Point(0, 1));

        assertFalse(placeAndMovePlayer(DEFAULT_SIZED_MAP, 0, 0, GameMap.NORTH));

        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(0, 1));
        assertEquals(null, DEFAULT_SIZED_MAP.getPosition(mockPlayer));

    }

    @Test
    public void moveMonsterOntoPlayerAndWinCombat() {

        when(mockPlayer.visit(mockMonster, DEFAULT_SIZED_MAP)).then(invocationOnMock -> {
            DEFAULT_SIZED_MAP.removePlayer(mockPlayer);
            return Combat.INITIATOR_WIN;
        });

        DEFAULT_SIZED_MAP.place(mockPlayer, new Point(0, 1));

        assertTrue(placeAndMoveMonster(DEFAULT_SIZED_MAP, 0, 0, GameMap.NORTH));

        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(0, 1));
        assertEquals(null, DEFAULT_SIZED_MAP.getPosition(mockPlayer));

    }

    @Test
    public void moveMonsterOntoPlayerAndLoseCombat() {

        when(mockPlayer.visit(mockMonster, DEFAULT_SIZED_MAP)).then(invocationOnMock -> {
            DEFAULT_SIZED_MAP.removeMonster(mockMonster);
            return Combat.INITIATOR_LOSS;
        });

        DEFAULT_SIZED_MAP.place(mockPlayer, new Point(0, 1));

        assertFalse(placeAndMoveMonster(DEFAULT_SIZED_MAP, 0, 0, GameMap.NORTH));

        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockPlayer), new Point(0, 1));
        assertEquals(null, DEFAULT_SIZED_MAP.getPosition(mockMonster));

    }
}
