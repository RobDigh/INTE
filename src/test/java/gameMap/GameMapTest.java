package gameMap;

import entity.Entity;
import entity.Monster;
import graph.Graph;
import graph.GraphFactory;
import org.junit.Before;
import entity.Player;

import java.awt.*;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// ToDo: Should this really be a superclass?
public class GameMapTest {

    protected final GameMap DEFAULT_SIZED_MAP = createDefaultSizedGameMap();

    protected Player mockPlayer = mock(Player.class);
    protected Monster mockMonster = mock(Monster.class);

    protected GameMap createDefaultSizedGameMap() {
        return new GameMap();
    }

    protected GameMap createCustomSizedGameMap(int xLength, int yLength) {
        return new GameMap(xLength, yLength);
    }

    protected GameMap placePlayer(GameMap gameMap, int x, int y) {

        Player player = mock(Player.class);
        gameMap.place(player, new Point(x, y));

        return gameMap;

    }

    protected GameMap createGameMapAndPlacePlayer(int x, int y) {

        Player player = mock(Player.class);

        GameMap gameMap = createDefaultSizedGameMap();
        gameMap.place(player, new Point(x, y));

        return gameMap;
    }

    protected boolean placeAndMovePlayer(GameMap gameMap, int x, int y, int direction) {

        gameMap.place(mockPlayer, new Point(x, y));
        return gameMap.move(mockPlayer, direction);

    }

    protected boolean placeAndMoveMonster(GameMap gameMap, int x, int y, int direction) {

        gameMap.place(mockMonster, new Point(x, y));
        return gameMap.move(mockMonster, direction);

    }

    @Before
    public void setup() {

        when(mockPlayer.getSpeed()).thenReturn(1);
        when(mockMonster.getSpeed()).thenReturn(1);

    }
}
