package entity.creature;

import entity.gameMap.GameMap;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;

public class AiTest {

    /**
     * First implementation:
     *      Flee to a empty position as far away as possible
     *      If no empty positions? Start combat with monster next to combat? Or stay?
     */

    private Creature testCreature;
    private InventoryFactory mockInventoryFactory = mock(InventoryFactory.class);
    private Ai ai;
    private GameMap gameMap;

    private Creature createCreature() {
        return new Creature(10, 2, 8, 5, 5, mockInventoryFactory, ai);
    }

    @Before
    public void setUp(){
        gameMap = new GameMap();
        ai = new Ai();
        testCreature = createCreature();
        gameMap.place(testCreature, new Point(5,5));
    }

    @Test
    public void testFlee(){
        testCreature.flee(gameMap);
        assertEquals(new Point(5,7), gameMap.getPosition(testCreature));
    }
}
