package entity;

import org.junit.Before;
import java.awt.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// ToDo: Should this really be a superclass?
public class GameMapTest {

    protected final GameMap DEFAULT_SIZED_MAP = createDefaultSizedGameMap();

    protected Creature mockCreature = mock(Creature.class);
    protected Creature mockMonster = mock(Creature.class);

    protected GameMap createDefaultSizedGameMap() {
        return new GameMap();
    }

    protected GameMap createCustomSizedGameMap(int xLength, int yLength) {
        return new GameMap(xLength, yLength);
    }

    protected GameMap placePlayer(GameMap gameMap, int x, int y) {

        Creature creature = mock(Creature.class);
        gameMap.place(creature, new Point(x, y));

        return gameMap;

    }

    protected GameMap createGameMapAndPlacePlayer(int x, int y) {

        Creature creature = mock(Creature.class);

        GameMap gameMap = createDefaultSizedGameMap();
        gameMap.place(creature, new Point(x, y));

        return gameMap;
    }

    protected boolean placeAndMovePlayer(GameMap gameMap, int x, int y, Point direction) {

        gameMap.place(mockCreature, new Point(x, y));
        return gameMap.move(mockCreature, direction);

    }

    protected boolean placeAndMoveMonster(GameMap gameMap, int x, int y, Point direction) {

        gameMap.place(mockMonster, new Point(x, y));
        return gameMap.move(mockMonster, direction);

    }

    @Before
    public void setup() {

        when(mockCreature.getSpeed()).thenReturn(1);
        when(mockMonster.getSpeed()).thenReturn(1);

    }
}
