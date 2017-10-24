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
    private Creature testPlayer;
    private Creature testMonster;

    private Creature createPlayer() {
        return new Creature(5, 8, 5, true, mockInventoryFactory, behaviour);
    }

    private Creature createMonster() {
        return new Creature(5, 8, 5, false, mockInventoryFactory, behaviour);
    }

    @Before
    public void setUp(){
        behaviour = new Behaviour();
        gameMap = new GameMap();
        testPlayer = createPlayer();
        testMonster = createMonster();
        gameMap.placeEntity(testPlayer, new Point(5,5));
    }

    @Test
    public void testSoFleeCallsGetAvailablePositionsFromGameMap(){
        ArrayList<Point> availablePositions = new ArrayList<>();
        availablePositions.add(new Point(2,2));
        availablePositions.add(new Point(3,3));
        when(mockGameMap.getAvailablePositions(testMonster)).thenReturn(availablePositions);

        testMonster.flee(mockGameMap);
        verify(mockGameMap).getAvailablePositions(testMonster);
    }

    @Test
    public void testFleeToFirstAvailablePointFindInListOfEmptyPoints(){
        ArrayList<Point> availablePositions = new ArrayList<>();
        availablePositions.add(new Point(2,2));
        availablePositions.add(new Point(3,3));
        when(mockGameMap.getAvailablePositions(testMonster)).thenReturn(availablePositions);

        testMonster.flee(mockGameMap);
        //verify(mockGameMap).fleeMove(testMonster, new Point(2,2));
    }

    @Test
    public void testSoActMethodCallsActInBehaviour(){
        Behaviour mockBehaviour = mock(Behaviour.class);
        testMonster = new Creature(5, 8, 5, false, mockInventoryFactory, mockBehaviour);
        testMonster.act(mockMonster);
        verify(mockBehaviour).act(mockMonster);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testNoEmptyPositionsAvailableFlee(){
        when(mockGameMap.getAvailablePositions(testMonster)).thenReturn(new ArrayList<>());
        testMonster.flee(mockGameMap);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void actNotImplemented(){
        testPlayer.act(mockMonster);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testBehaviourFleeWithPlayer(){
        testPlayer.flee(mockGameMap);
    }
}