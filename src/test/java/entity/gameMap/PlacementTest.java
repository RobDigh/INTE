package entity.gameMap;

import entity.creature.Ai;
import entity.creature.Creature;
import entity.creature.InventoryFactory;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class PlacementTest extends GameMapTest {

    private Creature testCreature;
    private InventoryFactory mockInventoryFactory = mock(InventoryFactory.class);
    private Ai ai = mock(Ai.class);
    GameMap gameMap;

    private Creature createCreature() {
        return new Creature(10, 2, 8, 5, 5, mockInventoryFactory, ai);
    }

    private GameMap createCustomGameMap(int xLength, int yLength){
        return new GameMap(xLength, yLength);
    }

    @Before
    public void setUp(){
        testCreature = createCreature();
        gameMap =  createDefaultSizedGameMap();
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

        gameMap.place(creature, new Point(0, 0));

        assertEquals(new Point(0, 0), gameMap.getPosition(creature));

    }

    @Test
    public void placeSeveralCreatures() {

        Creature m1 = mock(Creature.class);
        Creature m2 = mock(Creature.class);
        Creature m3 = mock(Creature.class);

        GameMap gameMap = createDefaultSizedGameMap();

        assertTrue(gameMap.place(m1, new Point(0, 0)));
        assertTrue(gameMap.place(m2, new Point(4, 3)));
        assertTrue(gameMap.place(m3, new Point(1, 8)));

    }

    @Test
    public void placeCreatureOnAnotherCreature() {

        Creature m1 = mock(Creature.class);
        Creature m2 = mock(Creature.class);

        GameMap gameMap = createDefaultSizedGameMap();

        assertTrue(gameMap.place(m1, new Point(0, 0)));
        assertFalse(gameMap.place(m2, new Point(0, 0)));

    }

    @Test
    public void getSeveralCreaturePositions() {

        Creature m1 = mock(Creature.class);
        Creature m2 = mock(Creature.class);
        Creature m3 = mock(Creature.class);

        GameMap gameMap = createDefaultSizedGameMap();

        gameMap.place(m1, new Point(0, 0));
        gameMap.place(m2, new Point(4, 3));
        gameMap.place(m3, new Point(1, 8));

        assertEquals(new Point(0, 0), gameMap.getPosition(m1));
        assertEquals(new Point(4, 3), gameMap.getPosition(m2));
        assertEquals(new Point(1, 8), gameMap.getPosition(m3));

    }

    @Test
    public void removeCreature() {

        placeCreature(DEFAULT_SIZED_MAP, 0, 0);
        DEFAULT_SIZED_MAP.remove(mockCreature);

        assertEquals(null, DEFAULT_SIZED_MAP.getPosition(mockCreature));

    }

    @Test
    public void testPlaceGameMap() {

        GameMap lowerLevel = createDefaultSizedGameMap();
        GameMap upperLevel = createDefaultSizedGameMap();

        upperLevel.place(lowerLevel, new Point(2, 2));

        assertEquals(new Point(2, 2), upperLevel.getPosition(lowerLevel));

    }

    @Test
    public void testPlaceItem() {

        DEFAULT_SIZED_MAP.place(mockItem, GameMap.DEFAULT_ENTRY_POSITION);
        assertEquals(GameMap.DEFAULT_ENTRY_POSITION, DEFAULT_SIZED_MAP.getPosition(mockItem));

    }

    @Test
    public void testGetEntityFromPosition() {
        gameMap.place(testCreature, new Point(0,0));
        assertEquals(testCreature, gameMap.getEntity(new Point(0,0)));
    }

    @Test
    public void testGetEntityFromEmptyPosition(){
        assertEquals(null, gameMap.getEntity(new Point(0,0)));
    }

    @Test
    public void testGetAllEmptyPositionsForSpecificPlayerOnEmptyMap(){
        gameMap = createCustomGameMap(2, 2);
        ArrayList<Point> availablePositions = new ArrayList<>();

        for(int x = 0; x < 2; x++){
            for(int y = 0; y < 2; y++){
                availablePositions.add(new Point(x, y));
            }
        }

        assertEquals(gameMap.getAvailablePositions(testCreature), availablePositions);
    }
}
