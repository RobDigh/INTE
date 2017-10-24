package entity.gameMap;

import entity.creature.Behaviour;
import entity.creature.Creature;
import entity.creature.InventoryFactory;
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

public class GetAvailableDirectionsTest extends GameMapTest {

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
    public void testGetAvailableDirectionsEmptyMap(){
        gameMap.placeEntity(testPlayer, new Point(3,3));
        ArrayList<Point> directions = new ArrayList<Point>();
        directions.add(new Point(0,1));
        directions.add(new Point(0,-1));
        directions.add(new Point(-1,0));
        directions.add(new Point(1,0));

        assertEquals(directions, gameMap.getAvailableDirections(testPlayer));
    }

    @Test
    public void testGetAvailableDirectionsWhileMonsterIsOnAValidDirection(){
        gameMap.placeEntity(testMonster, new Point(5,7));

        ArrayList<Point> availableDirections = gameMap.getAvailableDirections(testPlayer);
        assertFalse(availableDirections.contains(new Point(0,1)));
    }

    @Test
    public void testGetAvailableDirectionsWhileItemIsOnValidDirection(){
        Item item = new HealthPotion(5);
        gameMap.placeEntity(item, new Point(5,3));

        ArrayList<Point> validDirections = gameMap.getAvailableDirections(testPlayer);
        assertTrue(validDirections.contains(new Point(0,1)));
        assertTrue(validDirections.contains(new Point(0,-1)));
        assertTrue(validDirections.contains(new Point(-1,0)));
        assertTrue(validDirections.contains(new Point(1,0)));
    }

    @Test
    public void testGetAvailableDirectionsWhileBothItemAndMonstersAreOnValidDirections(){
        gameMap.placeEntity(new HealthPotion(5), new Point(3,5));
        gameMap.placeEntity(testMonster, new Point(7,5));
        ArrayList<Point> validDirections = gameMap.getAvailableDirections(testPlayer);

        assertTrue(validDirections.contains(new Point(0,1)));
        assertTrue(validDirections.contains(new Point(0,-1)));
        assertTrue(validDirections.contains(new Point(-1,0)));
        assertFalse(validDirections.contains(new Point(1,0)));
    }
}
