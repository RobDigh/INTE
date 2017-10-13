package entity.gameMap;

import combat.Combat;
import entity.Entity;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovementTest extends GameMapTest {

    private void checkResultingPositionForCreature(int resultingX, int resultingY) {
        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockCreature), new Point(resultingX, resultingY));
    }

    private void creatureMovementPerformedCorrectly(int originalX, int originalY,
                                                  Point direction,
                                                  int resultingX, int resultingY) {

        assertTrue(placeAndMoveCreature(DEFAULT_SIZED_MAP, originalX, originalY, direction));
        checkResultingPositionForCreature(resultingX, resultingY);

    }

    private void creatureMovementHinderedCorrectly(int originalX, int originalY,
                                                 Point direction,
                                                 int resultingX, int resultingY) {

        assertFalse(placeAndMoveCreature(DEFAULT_SIZED_MAP, originalX, originalY, direction));
        checkResultingPositionForCreature(resultingX, resultingY);

    }

    /*
     * Creature movement
     */

    @Test(expected = IllegalArgumentException.class)
    public void moveCreatureInInvalidDirection() {
        placeAndMoveCreature(DEFAULT_SIZED_MAP, 0, 0, new Point(2, 2));
    }

    @Test
    public void moveCreatureNorth() {
        creatureMovementPerformedCorrectly(0, 0, GameMap.NORTH, 0, 1);
    }

    @Test
    public void moveCreatureNorthOverEdge() {
        creatureMovementHinderedCorrectly(0, GameMap.DEFAULT_Y_LENGTH - 1, GameMap.NORTH,
                0, GameMap.DEFAULT_Y_LENGTH - 1);

    }

    @Test
    public void moveCreatureNorthOverEdgeOnCustomSizedMap() {

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertFalse(placeAndMoveCreature(gameMap, 0, 4, GameMap.NORTH));
        assertEquals(gameMap.getPosition(mockCreature), new Point(0,4));

    }

    @Test
    public void moveHighSpeedCreatureNorth() {

        when(mockCreature.getSpeed()).thenReturn(2);
        creatureMovementPerformedCorrectly(0, 0, GameMap.NORTH, 0, 2);

    }

    @Test
    public void moveHighSpeedCreatureNorthOverEdge() {

        when(mockCreature.getSpeed()).thenReturn(2);
        creatureMovementHinderedCorrectly(0, GameMap.DEFAULT_Y_LENGTH - 1, GameMap.NORTH, 0,GameMap.DEFAULT_Y_LENGTH - 1);

    }

    @Test
    public void moveHighSpeedCreatureNorthOverEdgeOnCustomSizedMap() {

        when(mockCreature.getSpeed()).thenReturn(2);

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertFalse(placeAndMoveCreature(gameMap, 0, 4, GameMap.NORTH));
        assertEquals(gameMap.getPosition(mockCreature), new Point(0,4));

    }

    @Test
    public void moveHighSpeedCreatureNorthToEdge() {

        when(mockCreature.getSpeed()).thenReturn(2);
        creatureMovementPerformedCorrectly(0, GameMap.DEFAULT_Y_LENGTH - 2, GameMap.NORTH, 0, GameMap.DEFAULT_Y_LENGTH - 1);

    }

    @Test
    public void moveHighSpeedCreatureNorthToEdgeOnCustomSizedMap() {

        when(mockCreature.getSpeed()).thenReturn(2);

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertTrue(placeAndMoveCreature(gameMap, 0, 3, GameMap.NORTH));
        assertEquals(new Point(0, 4), gameMap.getPosition(mockCreature));

    }

    @Test
    public void moveCreatureSouth() {
        creatureMovementPerformedCorrectly(0, 1, GameMap.SOUTH, 0, 0);
    }

    @Test
    public void moveCreatureOverSouthEdge() {
        creatureMovementHinderedCorrectly(0,0, GameMap.SOUTH, 0, 0);
    }

    @Test
    public void moveHighSpeedCreatureSouth() {

        when(mockCreature.getSpeed()).thenReturn(2);
        creatureMovementPerformedCorrectly(0, 2, GameMap.SOUTH, 0, 0);

    }

    @Test
    public void moveHighSpeedCreatureSouthFromRiskyPosition() {

        /*
         * This test was added to catch a bug resulting from:
         * int toSubtract = Math.max(0, Math.abs(playerPosition.y - player.getSpeed()));
         */

        when(mockCreature.getSpeed()).thenReturn(2);
        creatureMovementPerformedCorrectly(0, 4, GameMap.SOUTH, 0, 2);

    }

    @Test
    public void moveHighSpeedCreatureSouthOverEdge() {

        when(mockCreature.getSpeed()).thenReturn(2);
        creatureMovementHinderedCorrectly(0, 0, GameMap.SOUTH, 0, 0);

    }

    @Test
    public void moveHighSpeedCreatureSouthToEdge() {

        when(mockCreature.getSpeed()).thenReturn(2);
        creatureMovementPerformedCorrectly(0, 1, GameMap.SOUTH, 0, 0);

    }

    @Test
    public void moveCreatureWest() {
        creatureMovementPerformedCorrectly(0, 0, GameMap.WEST, 1, 0);
    }

    @Test
    public void moveCreatureWestOverEdge() {
        creatureMovementHinderedCorrectly(GameMap.DEFAULT_X_LENGTH - 1, 0, GameMap.WEST, GameMap.DEFAULT_X_LENGTH - 1, 0);
    }

    @Test
    public void moveCreatureWestOverEdgeOnCustomSizedMap() {

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertFalse(placeAndMoveCreature(gameMap, 4,0, GameMap.WEST));
        assertEquals(gameMap.getPosition(mockCreature), new Point(4, 0));

    }

    @Test
    public void moveHighSpeedCreatureWest() {

        when(mockCreature.getSpeed()).thenReturn(2);
        creatureMovementPerformedCorrectly(0, 0, GameMap.WEST, 2, 0);

    }

    @Test
    public void moveHighSpeedCreatureWestOverEdge() {

        when(mockCreature.getSpeed()).thenReturn(2);
        creatureMovementHinderedCorrectly(GameMap.DEFAULT_X_LENGTH - 1, 0, GameMap.WEST, GameMap.DEFAULT_X_LENGTH - 1, 0);

    }

    @Test
    public void moveHighSpeedCreatureWestOverEdgeOnCustomSizedMap() {

        when(mockCreature.getSpeed()).thenReturn(2);

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertFalse(placeAndMoveCreature(gameMap, 4, 0, GameMap.WEST));
        assertEquals(gameMap.getPosition(mockCreature), new Point(4,0));

    }

    @Test
    public void moveHighSpeedCreatureWestToEdge() {

        when(mockCreature.getSpeed()).thenReturn(2);
        creatureMovementPerformedCorrectly(GameMap.DEFAULT_X_LENGTH - 2, 0, GameMap.WEST, GameMap.DEFAULT_X_LENGTH - 1, 0);

    }

    @Test
    public void moveHighSpeedCreatureWestToEdgeOnCustomSizedMap() {

        when(mockCreature.getSpeed()).thenReturn(2);

        GameMap gameMap = createCustomSizedGameMap(5, 5);

        assertTrue(placeAndMoveCreature(gameMap, 3, 0, GameMap.WEST));
        assertEquals(new Point(4, 0), gameMap.getPosition(mockCreature));

    }

    @Test
    public void moveCreatureEast() {
        creatureMovementPerformedCorrectly(1, 0, GameMap.EAST, 0, 0);
    }

    @Test
    public void moveCreatureEastOverEdge() {
        creatureMovementHinderedCorrectly(0, 0, GameMap.EAST, 0, 0);
    }

    @Test
    public void moveHighSpeedCreatureEast() {

        when(mockCreature.getSpeed()).thenReturn(2);
        creatureMovementPerformedCorrectly(2, 0, GameMap.EAST, 0, 0);

    }

    @Test
    public void moveHighSpeedCreatureEastFromRiskyPosition() {

        when(mockCreature.getSpeed()).thenReturn(2);
        creatureMovementPerformedCorrectly(4, 0, GameMap.EAST, 2, 0);

    }

    @Test
    public void moveHighSpeedCreatureEastOverEdge() {

        when(mockCreature.getSpeed()).thenReturn(2);
        creatureMovementHinderedCorrectly(0, 0, GameMap.EAST, 0, 0);

    }

    @Test
    public void moveHighSpeedCreatureEastToEdge() {

        when(mockCreature.getSpeed()).thenReturn(2);
        creatureMovementPerformedCorrectly(1, 0, GameMap.EAST, 0, 0);

    }

    /*
     * Collision
     */

    private boolean removeAcceptant(InvocationOnMock invocation) {

        GameMap gameMap = invocation.getArgument(1);
        //gameMap.remove((Entity) invocation.getMock());

        return Combat.INITIATOR_WIN;

    }

    private boolean removeVisitor(InvocationOnMock invocation) {

        GameMap gameMap = invocation.getArgument(1);
        Entity visitor = invocation.getArgument(0);

        //gameMap.remove(visitor);

        return Combat.INITIATOR_LOSS;

    }

    @Test
    public void moveCreatureOntoCreatureAndWinCombat() {

        when(mockMonster.accept(mockCreature)).then(this::removeAcceptant);

        DEFAULT_SIZED_MAP.place(mockMonster, new Point(0, 1));

        assertTrue(placeAndMoveCreature(DEFAULT_SIZED_MAP, 0, 0, GameMap.NORTH));

        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockCreature), new Point(0, 1));
        assertEquals(null, DEFAULT_SIZED_MAP.getPosition(mockMonster));

    }

    @Test
    public void moveCreatureOntoCreatureAndLoseCombat() {

        when(mockMonster.accept(mockCreature)).then(this::removeVisitor);

        DEFAULT_SIZED_MAP.place(mockMonster, new Point(0, 1));

        assertFalse(placeAndMoveCreature(DEFAULT_SIZED_MAP, 0, 0, GameMap.NORTH));

        assertEquals(DEFAULT_SIZED_MAP.getPosition(mockMonster), new Point(0, 1));
        assertEquals(null, DEFAULT_SIZED_MAP.getPosition(mockCreature));

    }
}
