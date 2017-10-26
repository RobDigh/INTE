package entity.creature;

import entity.gameMap.GameMap;
import entity.item.Item;
import entity.item.consumable.hp.HealthPotion;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
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

    @Test(expected = IllegalArgumentException.class)
        public void testFleeWithNullCreature(){
            behaviour.flee(null, gameMap, true);
        }

    @Test(expected = IllegalArgumentException.class)
    public void testFleeNullGameMap(){
        behaviour.flee(testPlayer, null, true);
    }


    @Test
    public void testFleeToInFirstAvailableDirectionFindInListOfValidDirections(){
        ArrayList<Point> validDirections = new ArrayList<>();
        validDirections.add(new Point(0,1));
        validDirections.add(new Point(0,-1));
        validDirections.add(new Point(-1,0));
        validDirections.add(new Point(1,0));

        when(mockGameMap.getAvailableDirections(testMonster)).thenReturn(validDirections);
        testMonster.flee(mockGameMap);

        verify(mockGameMap).move(testMonster, new Point(0,1));
    }

    @Test
    public void testSoActMethodCallsActInBehaviour(){
        Behaviour mockBehaviour = mock(Behaviour.class);
        testMonster = new Creature(5, 8, 5, false, mockInventoryFactory, mockBehaviour);
        testMonster.act(mockMonster);
        verify(mockBehaviour).act(mockMonster);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testNoEmptyDirectionsAvailableFlee(){
        when(mockGameMap.getAvailableDirections(testMonster)).thenReturn(new ArrayList<>());
        testMonster.flee(mockGameMap);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testBehaviourActMethodNotImplemented(){
        testPlayer.act(mockMonster);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testBehaviourFleeWithPlayerMethodNotImplemented(){
        testPlayer.flee(mockGameMap);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testActWithNullCreature(){
        behaviour.act(null);
    }
}