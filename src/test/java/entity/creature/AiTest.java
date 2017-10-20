package entity.creature;

import entity.gameMap.GameMap;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AiTest {

    /**
     * First implementation: Flee to first empty position
     * TODO: Implement AI logic
     */

    private GameMap mockGameMap = mock(GameMap.class);
    private Creature mockMonster = mock(Creature.class);
    private InventoryFactory mockInventoryFactory = mock(InventoryFactory.class);

    private Ai ai;
    private GameMap gameMap;
    private Creature testCreature;

    private Creature createCreature() {
        return new Creature(10, 2, 8, 5, 5, true, mockInventoryFactory, ai);
    }

    @Before
    public void setUp(){
        ai = new Ai();
        gameMap = new GameMap();
        testCreature = createCreature();
        gameMap.place(testCreature, new Point(5,5));
    }

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

    @Test
    public void testSoActMethodCallsActInAi(){
        Ai mockAi = mock(Ai.class);
        testCreature = new Creature(10, 2, 8, 5, 5, true, mockInventoryFactory, mockAi);
        testCreature.act(mockMonster);
        verify(mockAi).act();
    }

    @Test (expected = UnsupportedOperationException.class)
    public void actNotImplemented(){
        testCreature.act(mockMonster);
    }
}