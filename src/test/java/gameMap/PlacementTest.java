package gameMap;

import entity.Monster;
import org.junit.Test;
import entity.Player;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class PlacementTest extends GameMapTest {

    private GameMap createGameMapAndPlaceMonster(int x, int y) {

        Monster monster = mock(Monster.class);

        GameMap gameMap = createDefaultSizedGameMap();
        gameMap.place(monster, new Point(x, y));

        return gameMap;

    }

    private void placeMonster(GameMap gameMap, int x, int y) {

        Monster monster = mock(Monster.class);
        gameMap.place(monster, new Point(x, y));

    }

    @Test
    public void placePlayer() {

        Player player = mock(Player.class);

        GameMap gameMap = createDefaultSizedGameMap();

        assertTrue(gameMap.place(player, new Point(0, 0)));

    }

    @Test
    public void placePlayerTwice() {

        Player player = mock(Player.class);

        GameMap gameMap = createDefaultSizedGameMap();

        gameMap.place(player, new Point(0, 0));
        assertFalse(gameMap.place(player, new Point(0, 0)));

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

    @Test
    public void placeMonster() {

        Monster monster = mock(Monster.class);

        GameMap gameMap = createDefaultSizedGameMap();

        assertTrue(gameMap.place(monster, new Point(0, 0)));

    }

    @Test
    public void placeSeveralMonsters() {

        Monster m1 = mock(Monster.class);
        Monster m2 = mock(Monster.class);
        Monster m3 = mock(Monster.class);

        GameMap gameMap = createDefaultSizedGameMap();

        assertTrue(gameMap.place(m1, new Point(0, 0)));
        assertTrue(gameMap.place(m2, new Point(4, 3)));
        assertTrue(gameMap.place(m3, new Point(1, 8)));

    }

    @Test
    public void placeMonsterOnAnotherMonster() {

        Monster m1 = mock(Monster.class);
        Monster m2 = mock(Monster.class);

        GameMap gameMap = createDefaultSizedGameMap();

        assertTrue(gameMap.place(m1, new Point(0, 0)));
        assertFalse(gameMap.place(m2, new Point(0, 0)));

    }

    @Test(expected = IllegalArgumentException.class)
    public void placeMonsterAtNegativeX() {
        createGameMapAndPlaceMonster(-1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeMonsterAtNegativeY() {
        createGameMapAndPlaceMonster(0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeMonsterAtTooGreatX() {
        createGameMapAndPlaceMonster(GameMap.DEFAULT_X_LENGTH, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeMonsterAtTooGreatY() {
        createGameMapAndPlaceMonster(0, GameMap.DEFAULT_Y_LENGTH);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeMonsterAtTooGreatXOnCustomSizedMap() {
        placeMonster(createCustomSizedGameMap(5, 5), 5, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeMonsterAtTooGreatYOnCustomSizedMap() {
        placeMonster(createCustomSizedGameMap(5, 5), 0, 5);
    }

    @Test
    public void getMonsterPosition() {

        Monster monster = mock(Monster.class);

        GameMap gameMap = createDefaultSizedGameMap();

        gameMap.place(monster, new Point(0, 0));

        assertEquals(new Point(0, 0), gameMap.getPosition(monster));

    }

    @Test
    public void getSeveralMonsterPositions() {

        Monster m1 = mock(Monster.class);
        Monster m2 = mock(Monster.class);
        Monster m3 = mock(Monster.class);

        GameMap gameMap = createDefaultSizedGameMap();

        gameMap.place(m1, new Point(0, 0));
        gameMap.place(m2, new Point(4, 3));
        gameMap.place(m3, new Point(1, 8));

        assertEquals(new Point(0, 0), gameMap.getPosition(m1));
        assertEquals(new Point(4, 3), gameMap.getPosition(m2));
        assertEquals(new Point(1, 8), gameMap.getPosition(m3));

    }

    @Test
    public void placePlayerOnMonster() {

        Monster m1 = mock(Monster.class);
        Player player = mock(Player.class);

        GameMap gameMap = createDefaultSizedGameMap();

        gameMap.place(m1, new Point(0, 0));
        assertFalse(gameMap.place(player, new Point(0, 0)));

    }

    @Test
    public void placeMonsterOnPlayer() {

        Monster m1 = mock(Monster.class);
        Player player = mock(Player.class);

        GameMap gameMap = createDefaultSizedGameMap();

        gameMap.place(player, new Point(0, 0));
        assertFalse(gameMap.place(m1, new Point(0, 0)));

    }

    @Test
    public void removePlayer() {

        placePlayer(DEFAULT_SIZED_MAP, 0, 0);
        DEFAULT_SIZED_MAP.remove(mockPlayer);

        assertEquals(null, DEFAULT_SIZED_MAP.getPosition(mockPlayer));

    }

    @Test
    public void testPlaceGameMap() {

        GameMap lowerLevel = createDefaultSizedGameMap();
        GameMap upperLevel = createDefaultSizedGameMap();

        upperLevel.place(lowerLevel, new Point(2, 2));

        assertEquals(new Point(2, 2), upperLevel.getPosition(lowerLevel));

    }
}
