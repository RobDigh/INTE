package gameMap;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameMapTest {

    @Test
    public void constructGameMapWithEmptyConstructor() {

        GameMap gameMap = new GameMap();

        assertEquals(GameMap.DEFAULT_X_LENGTH, gameMap.getXLength());
        assertEquals(GameMap.DEFAULT_Y_LENGTH, gameMap.getYLength());

    }
}
