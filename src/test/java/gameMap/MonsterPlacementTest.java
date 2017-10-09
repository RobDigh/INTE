package gameMap;

import npc.Monster;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class MonsterPlacementTest extends GameMapTest {

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
    public void placeMonster() {

        Monster monster = mock(Monster.class);

        GameMap gameMap = createDefaultSizedGameMap();

        assertTrue(gameMap.place(monster, new Point(0, 0)));

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
}
