package entity.gameMap;

import entity.creature.Behaviour;
import entity.creature.Creature;
import entity.creature.InventoryFactory;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class PlacementTest extends GameMapTest {

    private Creature testCreature;
    private Creature mockCreature = mock(Creature.class);
    private InventoryFactory mockInventoryFactory = mock(InventoryFactory.class);
    private Behaviour behaviour = mock(Behaviour.class);
    GameMap gameMap;

    private Creature createCreature() {
        return new Creature(6, 7, 5, true, mockInventoryFactory, behaviour);
    }

    @Before
    public void setUp() {
        testCreature = createCreature();
        gameMap = createDefaultSizedGameMap();
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeCreatureAtNegativeX() {
        createGameMapAndPlaceCreature(-1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeCreatureAtNegativeY() {
        createGameMapAndPlaceCreature(0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeCreatureAtTooGreatX() {
        createGameMapAndPlaceCreature(GameMap.DEFAULT_X_LENGTH, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeCreatureAtTooGreatY() {
        createGameMapAndPlaceCreature(0, GameMap.DEFAULT_Y_LENGTH);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeCreatureAtTooGreatXOnCustomSizedMap() {
        placeCreature(createCustomSizedGameMap(5, 5), 5, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeCreatureAtTooGreatYOnCustomSizedMap() {
        placeCreature(createCustomSizedGameMap(5, 5), 0, 5);
    }

    @Test
    public void getCreaturePosition() {

        Creature creature = mock(Creature.class);

        GameMap gameMap = createDefaultSizedGameMap();

        gameMap.placeEntity(creature, new Point(0, 0));

        assertEquals(new Point(0, 0), gameMap.getPosition(creature));

    }

    @Test
    public void placeSeveralCreatures() {

        Creature m1 = mock(Creature.class);
        Creature m2 = mock(Creature.class);
        Creature m3 = mock(Creature.class);

        GameMap gameMap = createDefaultSizedGameMap();

        assertTrue(gameMap.placeEntity(m1, new Point(0, 0)));
        assertTrue(gameMap.placeEntity(m2, new Point(4, 3)));
        assertTrue(gameMap.placeEntity(m3, new Point(1, 8)));

    }

    @Test
    public void placeCreatureOnAnotherCreature() {

        Creature m1 = mock(Creature.class);
        Creature m2 = mock(Creature.class);

        GameMap gameMap = createDefaultSizedGameMap();

        assertTrue(gameMap.placeEntity(m1, new Point(0, 0)));
        assertFalse(gameMap.placeEntity(m2, new Point(0, 0)));

    }

    @Test
    public void getSeveralCreaturePositions() {

        Creature m1 = mock(Creature.class);
        Creature m2 = mock(Creature.class);
        Creature m3 = mock(Creature.class);

        GameMap gameMap = createDefaultSizedGameMap();

        gameMap.placeEntity(m1, new Point(0, 0));
        gameMap.placeEntity(m2, new Point(4, 3));
        gameMap.placeEntity(m3, new Point(1, 8));

        assertEquals(new Point(0, 0), gameMap.getPosition(m1));
        assertEquals(new Point(4, 3), gameMap.getPosition(m2));
        assertEquals(new Point(1, 8), gameMap.getPosition(m3));

    }

    @Test(expected = IllegalArgumentException.class)
    public void getPositionOfNullCreature() { // ToDo: getPosition() should probably have its own suite
        DEFAULT_SIZED_MAP.getPosition(null);
    }

    @Test
    public void removeCreature() {

        DEFAULT_SIZED_MAP.placeEntity(mockCreature, new Point(0, 0));
        assertTrue(DEFAULT_SIZED_MAP.remove(mockCreature));

        assertEquals(null, DEFAULT_SIZED_MAP.getPosition(mockCreature));

    }

    @Test
    public void testRemoveNonExistingCreatureFromOccupiedPoint() {

        DEFAULT_SIZED_MAP.placeEntity(mockCreature, new Point(0, 0));

        Creature anotherMockCreature = mock(Creature.class);

        assertFalse(DEFAULT_SIZED_MAP.remove(anotherMockCreature));
        assertEquals(new Point(0, 0), DEFAULT_SIZED_MAP.getPosition(mockCreature));

    }

    @Test
    public void testRemoveNonExistingCreateFromUnoccupiedPoint() {

        Creature anotherMockCreature = mock(Creature.class);
        assertFalse(DEFAULT_SIZED_MAP.remove(anotherMockCreature));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullCreature() {
        DEFAULT_SIZED_MAP.remove(null);
    }

    @Test
    public void testPlaceGameMap() {

        GameMap lowerLevel = createDefaultSizedGameMap();
        GameMap upperLevel = createDefaultSizedGameMap();

        upperLevel.placeEntity(lowerLevel, new Point(2, 2));

        assertEquals(new Point(2, 2), upperLevel.getPosition(lowerLevel));

    }

    @Test
    public void testPlaceItem() {

        DEFAULT_SIZED_MAP.placeEntity(mockItem, GameMap.DEFAULT_ENTRY_POSITION);
        assertEquals(GameMap.DEFAULT_ENTRY_POSITION, DEFAULT_SIZED_MAP.getPosition(mockItem));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlaceNullCreature() {
        DEFAULT_SIZED_MAP.placeEntity(null, new Point(0, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlaceCreatureAtNullPoint() {
        DEFAULT_SIZED_MAP.placeEntity(mockCreature, null);
    }

    @Test
    public void testGetEntityFromPosition() {
        gameMap.placeEntity(testCreature, new Point(0, 0));
        assertEquals(testCreature, gameMap.getEntity(new Point(0, 0)));
    }

    @Test
    public void testGetEntityFromEmptyPosition() {
        assertEquals(null, gameMap.getEntity(new Point(0, 0)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetEntityFromNullPosition() {
        DEFAULT_SIZED_MAP.getEntity(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAvailableDirectionsWithNullCreature() {
        gameMap.getAvailableDirections(null);
    }
}

