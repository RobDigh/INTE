package entity.creature;

import entity.gameMap.GameMap;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AiTest {

    /**
     * First implementation:
     *      Flee to a empty position
     *      If no empty positions? Start combat with monster next to combat? Or stay?
     */

    private Creature testCreature;
    private Creature mockMonster = mock(Creature.class);
    private InventoryFactory mockInventoryFactory = mock(InventoryFactory.class);
    private Ai ai;
    private GameMap gameMap;
    private GameMap mockGameMap = mock(GameMap.class);

    private Creature createCreature() {
        return new Creature(10, 2, 8, 5, 5, true, mockInventoryFactory, ai);
    }

    private Creature monster(){
        return new Creature(8, 10, 6, 6, 6, false, mockInventoryFactory, ai);
    }

    @Before
    public void setUp(){
        gameMap = new GameMap();
        ai = new Ai();
        testCreature = createCreature();
        gameMap.place(testCreature, new Point(5,5));
    }

//    @Test
//    public void testFlee(){
//        testCreature.flee(gameMap);
//        assertEquals(new Point(5,7), gameMap.getPosition(testCreature));
//    }
//
//    @Test
//    public void testFleeWhileAnotherCreatureIsOnTheFirstPoint(){
//
//        when(mockGameMap.getEntity(new Point(5,7))).thenReturn(mockMonster);
//        mockGameMap.place(testCreature, new Point(5,5));
//
//        testCreature.flee(mockGameMap);
//        assertEquals(new Point(7,5), mockGameMap.getPosition(testCreature));
//    }

    @Test
    public void testSoFleeCallsGetAvailablePositionsFromGameMap(){
        testCreature.flee(mockGameMap);
        verify(mockGameMap).getAvailablePositions(testCreature);
    }

    @Test
    public void testFleeToFirstAvailablePointFindInListOfEmptyPoints(){
        ArrayList<Point> availablePositions = new ArrayList<>();
        availablePositions.add(new Point(2,2));
        availablePositions.add(new Point(3,3));
        when(mockGameMap.getAvailablePositions(testCreature)).thenReturn(availablePositions);

        testCreature.flee(mockGameMap);
        verify(mockGameMap).fleeMove(testCreature, new Point(2,2));
    }
}
