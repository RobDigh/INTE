package entity;

import combat.Combat;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovementTest extends GameMapTest {

    private void checkResultingPositionForPlayer(int resultingX, int resultingY) {
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockCreature), new Point(resultingX, resultingY));
    }

    private void checkResultingPositionForMonster(int resultingX, int resultingY) {
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(resultingX, resultingY));
    }

    private void playerMovementPerformedCorrectly(int originalX, int originalY,
                                                  Point direction,
                                                  int resultingX, int resultingY) {

        assertTrue(placeAndMovePlayer(DEFAULT_SIZED_MAP, originalX, originalY, direction));
        checkResultingPositionForPlayer(resultingX, resultingY);

    }

    private void playerMovementHinderedCorrectly(int originalX, int originalY,
                                                 Point direction,
                                                 int resultingX, int resultingY) {

        assertFalse(placeAndMovePlayer(DEFAULT_SIZED_MAP, originalX, originalY, direction));
        checkResultingPositionForPlayer(resultingX, resultingY);

    }

    private void monsterMovementPerformedCorrectly(int originalX, int originalY,
                                                  Point direction,
                                                  int resultingX, int resultingY) {

        assertTrue(placeAndMoveMonster(DEFAULT_SIZED_MAP, originalX, originalY, direction));
        checkResultingPositionForMonster(resultingX, resultingY);

    }

    private void monsterMovementHinderedCorrectly(int originalX, int originalY,
                                                 Point direction,
                                                 int resultingX, int resultingY) {

        assertFalse(placeAndMoveMonster(DEFAULT_SIZED_MAP, originalX, originalY, direction));
        checkResultingPositionForMonster(resultingX, resultingY);

    }

    /*
     * Creature movement
     */

    @Test(expected = IllegalArgumentException.class)
    public void movePlayerInInvalidDirection() {
        placeAndMovePlayer(DEFAULT_SIZED_MAP, 0, 0, new Point(2, 2));
    }

    @Test
    public void movePlayerNorth() {
        playerMovementPerformedCorrectly(0, 0, GameMap.NORTH, 0, 1);
    }

    @Test
    public void movePlayerNorthOverEdge() {
        playerMovementHinderedCorrectly(0, GameMap.DEFAULT_Y_LENGTH - 1, GameMap.NORTH,
                0, GameMap.DEFAULT_Y_LENGTH - 1);

    }

    @Test
    public void movePlayerNorthOverEdgeOnCustomSizedMap() {

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertFalse(placeAndMovePlayer(gameMap, 0, 4, GameMap.NORTH));
        assertEquals(gameMap.getPosition(mockCreature), new Point(0,4));

    }

    @Test
    public void moveHighSpeedPlayerNorth() {

        when(mockCreature.getSpeed()).thenReturn(2);
        playerMovementPerformedCorrectly(0, 0, GameMap.NORTH, 0, 2);

    }

    @Test
    public void moveHighSpeedPlayerNorthOverEdge() {

        when(mockCreature.getSpeed()).thenReturn(2);
        playerMovementHinderedCorrectly(0, GameMap.DEFAULT_Y_LENGTH - 1, GameMap.NORTH, 0,GameMap.DEFAULT_Y_LENGTH - 1);

    }

    @Test
    public void moveHighSpeedPlayerNorthOverEdgeOnCustomSizedMap() {

        when(mockCreature.getSpeed()).thenReturn(2);

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertFalse(placeAndMovePlayer(gameMap, 0, 4, GameMap.NORTH));
        assertEquals(gameMap.getPosition(mockCreature), new Point(0,4));

    }

    @Test
    public void moveHighSpeedPlayerNorthToEdge() {

        when(mockCreature.getSpeed()).thenReturn(2);
        playerMovementPerformedCorrectly(0, GameMap.DEFAULT_Y_LENGTH - 2, GameMap.NORTH, 0, GameMap.DEFAULT_Y_LENGTH - 1);

    }

    @Test
    public void moveHighSpeedPlayerNorthToEdgeOnCustomSizedMap() {

        when(mockCreature.getSpeed()).thenReturn(2);

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertTrue(placeAndMovePlayer(gameMap, 0, 3, GameMap.NORTH));
        assertEquals(new Point(0, 4), gameMap.getPosition(mockCreature));

    }

    @Test
    public void movePlayerSouth() {
        playerMovementPerformedCorrectly(0, 1, GameMap.SOUTH, 0, 0);
    }

    @Test
    public void movePlayerOverSouthEdge() {
        playerMovementHinderedCorrectly(0,0, GameMap.SOUTH, 0, 0);
    }

    @Test
    public void moveHighSpeedPlayerSouth() {

        when(mockCreature.getSpeed()).thenReturn(2);
        playerMovementPerformedCorrectly(0, 2, GameMap.SOUTH, 0, 0);

    }

    @Test
    public void moveHighSpeedPlayerSouthFromRiskyPosition() {

        /*
         * This test was added to catch a bug resulting from:
         * int toSubtract = Math.max(0, Math.abs(playerPosition.y - player.getSpeed()));
         */

        when(mockCreature.getSpeed()).thenReturn(2);
        playerMovementPerformedCorrectly(0, 4, GameMap.SOUTH, 0, 2);

    }

    @Test
    public void moveHighSpeedPlayerSouthOverEdge() {

        when(mockCreature.getSpeed()).thenReturn(2);
        playerMovementHinderedCorrectly(0, 0, GameMap.SOUTH, 0, 0);

    }

    @Test
    public void moveHighSpeedPlayerSouthToEdge() {

        when(mockCreature.getSpeed()).thenReturn(2);
        playerMovementPerformedCorrectly(0, 1, GameMap.SOUTH, 0, 0);

    }

    @Test
    public void movePlayerWest() {
        playerMovementPerformedCorrectly(0, 0, GameMap.WEST, 1, 0);
    }

    @Test
    public void movePlayerWestOverEdge() {
        playerMovementHinderedCorrectly(GameMap.DEFAULT_X_LENGTH - 1, 0, GameMap.WEST, GameMap.DEFAULT_X_LENGTH - 1, 0);
    }

    @Test
    public void movePlayerWestOverEdgeOnCustomSizedMap() {

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertFalse(placeAndMovePlayer(gameMap, 4,0, GameMap.WEST));
        assertEquals(gameMap.getPosition(mockCreature), new Point(4, 0));

    }

    @Test
    public void moveHighSpeedPlayerWest() {

        when(mockCreature.getSpeed()).thenReturn(2);
        playerMovementPerformedCorrectly(0, 0, GameMap.WEST, 2, 0);

    }

    @Test
    public void moveHighSpeedPlayerWestOverEdge() {

        when(mockCreature.getSpeed()).thenReturn(2);
        playerMovementHinderedCorrectly(GameMap.DEFAULT_X_LENGTH - 1, 0, GameMap.WEST, GameMap.DEFAULT_X_LENGTH - 1, 0);

    }

    @Test
    public void moveHighSpeedPlayerWestOverEdgeOnCustomSizedMap() {

        when(mockCreature.getSpeed()).thenReturn(2);

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertFalse(placeAndMovePlayer(gameMap, 4, 0, GameMap.WEST));
        assertEquals(gameMap.getPosition(mockCreature), new Point(4,0));

    }

    @Test
    public void moveHighSpeedPlayerWestToEdge() {

        when(mockCreature.getSpeed()).thenReturn(2);
        playerMovementPerformedCorrectly(GameMap.DEFAULT_X_LENGTH - 2, 0, GameMap.WEST, GameMap.DEFAULT_X_LENGTH - 1, 0);

    }

    @Test
    public void moveHighSpeedPlayerWestToEdgeOnCustomSizedMap() {

        when(mockCreature.getSpeed()).thenReturn(2);

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertTrue(placeAndMovePlayer(gameMap, 3, 0, GameMap.WEST));
        assertEquals(new Point(4, 0), gameMap.getPosition(mockCreature));

    }

    @Test
    public void movePlayerEast() {
        playerMovementPerformedCorrectly(1, 0, GameMap.EAST, 0, 0);
    }

    @Test
    public void movePlayerEastOverEdge() {
        playerMovementHinderedCorrectly(0, 0, GameMap.EAST, 0, 0);
    }

    @Test
    public void moveHighSpeedPlayerEast() {

        when(mockCreature.getSpeed()).thenReturn(2);
        playerMovementPerformedCorrectly(2, 0, GameMap.EAST, 0, 0);

    }

    @Test
    public void moveHighSpeedPlayerEastFromRiskyPosition() {

        when(mockCreature.getSpeed()).thenReturn(2);
        playerMovementPerformedCorrectly(4, 0, GameMap.EAST, 2, 0);

    }

    @Test
    public void moveHighSpeedPlayerEastOverEdge() {

        when(mockCreature.getSpeed()).thenReturn(2);
        playerMovementHinderedCorrectly(0, 0, GameMap.EAST, 0, 0);

    }

    @Test
    public void moveHighSpeedPlayerEastToEdge() {

        when(mockCreature.getSpeed()).thenReturn(2);
        playerMovementPerformedCorrectly(1, 0, GameMap.EAST, 0, 0);

    }

    /*
     * Collision
     */

    private boolean removeAcceptant(InvocationOnMock invocation) {

        GameMap gameMap = invocation.getArgument(1);
        gameMap.remove((Entity) invocation.getMock());

        return Combat.INITIATOR_WIN;

    }

    private boolean removeVisitor(InvocationOnMock invocation) {

        GameMap gameMap = invocation.getArgument(1);
        Entity visitor = invocation.getArgument(0);

        gameMap.remove(visitor);

        return Combat.INITIATOR_LOSS;

    }

    @Test
    public void movePlayerOntoMonsterAndWinCombat() {

        when(mockMonster.accept(mockCreature, DEFAULT_SIZED_MAP)).then(this::removeAcceptant);

        DEFAULT_SIZED_MAP.place(mockMonster, new Point(0, 1));

        assertTrue(placeAndMovePlayer(DEFAULT_SIZED_MAP, 0, 0, GameMap.NORTH));

        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockCreature), new Point(0, 1));
        assertEquals(null, DEFAULT_SIZED_MAP.getPosition(mockMonster));

    }

    @Test
    public void movePlayerOntoMonsterAndLoseCombat() {

        when(mockMonster.accept(mockCreature, DEFAULT_SIZED_MAP)).then(this::removeVisitor);

        DEFAULT_SIZED_MAP.place(mockMonster, new Point(0, 1));

        assertFalse(placeAndMovePlayer(DEFAULT_SIZED_MAP, 0, 0, GameMap.NORTH));

        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(0, 1));
        assertEquals(null, DEFAULT_SIZED_MAP.getPosition(mockCreature));

    }

    @Test
    public void moveMonsterOntoPlayerAndWinCombat() {

        when(mockCreature.accept(mockMonster, DEFAULT_SIZED_MAP)).then(this::removeAcceptant);

        DEFAULT_SIZED_MAP.place(mockCreature, new Point(0, 1));

        assertTrue(placeAndMoveMonster(DEFAULT_SIZED_MAP, 0, 0, GameMap.NORTH));

        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(0, 1));
        assertEquals(null, DEFAULT_SIZED_MAP.getPosition(mockCreature));

    }

    @Test
    public void moveMonsterOntoPlayerAndLoseCombat() {

        when(mockCreature.accept(mockMonster, DEFAULT_SIZED_MAP)).then(this::removeVisitor);

        DEFAULT_SIZED_MAP.place(mockCreature, new Point(0, 1));

        assertFalse(placeAndMoveMonster(DEFAULT_SIZED_MAP, 0, 0, GameMap.NORTH));

        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockCreature), new Point(0, 1));
        assertEquals(null, DEFAULT_SIZED_MAP.getPosition(mockMonster));

    }
}
