package entity;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class PlacementTest extends GameMapTest {

    private GameMap createGameMapAndPlaceCreature(int x, int y) {

        Creature creature = mock(Creature.class);

        GameMap gameMap = createDefaultSizedGameMap();
        gameMap.place(creature, new Point(x, y));

        return gameMap;

    }

    private void placeCreature(GameMap gameMap, int x, int y) {

        Creature creature = mock(Creature.class);
        gameMap.place(creature, new Point(x, y));

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
    public void removePlayer() {

        placePlayer(DEFAULT_SIZED_MAP, 0, 0);
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
}
