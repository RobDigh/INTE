package gameMap;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameMapTest {

    private GameMap createDefaultSizedGameMap() {
        return new GameMap();
    }

    private GameMap createCustomSizedGameMap(int xLength, int yLength) {
        return new GameMap(xLength, yLength);
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
}
