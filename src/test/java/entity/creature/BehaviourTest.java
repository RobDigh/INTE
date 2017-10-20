package entity.creature;

import entity.gameMap.GameMap;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BehaviourTest {

    private GameMap mockGameMap = mock(GameMap.class);
    private Creature mockMonster = mock(Creature.class);
    private InventoryFactory mockInventoryFactory = mock(InventoryFactory.class);

    private Behaviour behaviour;
    private GameMap gameMap;
    private Creature testCreature;

    private Creature createCreature() {
        return new Creature(10, 2, 8, 5, 5, true, mockInventoryFactory, behaviour);
    }

    @Before
    public void setUp(){
        behaviour = new Behaviour();
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
    public void testSoActMethodCallsActInBehaviour(){
        Behaviour mockBehaviour = mock(Behaviour.class);
        testCreature = new Creature(10, 2, 8, 5, 5, false, mockInventoryFactory, mockBehaviour);
        testCreature.act(mockMonster);
        verify(mockBehaviour).act();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void actNotImplemented(){
        testCreature.act(mockMonster);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testBehaviourFleeWithPlayer(){
        testCreature.flee(mockGameMap);
    }
}