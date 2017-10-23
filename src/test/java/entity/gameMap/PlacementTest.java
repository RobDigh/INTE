package entity.gameMap;

import entity.creature.Behaviour;
import entity.creature.Creature;
import entity.creature.InventoryFactory;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class PlacementTest extends GameMapTest {

    private Creature testCreature;
    private Creature mockCreature = mock(Creature.class);
    private InventoryFactory mockInventoryFactory = mock(InventoryFactory.class);
    private Behaviour behaviour = mock(Behaviour.class);
    GameMap gameMap;

    private Creature createCreature() {
        return new Creature(5, 8, 5, true, mockInventoryFactory, behaviour);
    }

    private GameMap createCustomGameMap(int xLength, int yLength){
        return new GameMap(xLength, yLength);
    }


    private List<Point> placeSeveralCreaturesOnMap(){

        List<Point> occupiedPoints = new ArrayList<>();

        Point p = new Point(1,1);
        Point p1 = new Point(5,7);
        Point p2 = new Point(3,1);
        Point p3 = new Point(5,2);

        occupiedPoints.add(p);
        occupiedPoints.add(p1);
        occupiedPoints.add(p2);
        occupiedPoints.add(p3);

        gameMap.place(testCreature, p);
        gameMap.place(mock(Creature.class), p1);
        gameMap.place(mock(Creature.class), p2);
        gameMap.place(mock(Creature.class), p3);

        return occupiedPoints;
    }

    private ArrayList<Point> getAllEmptyPoints(int xAxis, int yAxis, List<Point> occupiedPoints){
        ArrayList<Point> availablePositions = new ArrayList<>();

        for(int x = 0; x < xAxis; x++){
            for(int y = 0; y < yAxis; y++){
                if(!occupiedPoints.contains(new Point(x, y))){
                    availablePositions.add(new Point(x, y));
                }
            }
        }
        return availablePositions;
    }

    private boolean pointExistInList(List<Point> emptyPositions, List<Point> occupiedPoints){
        for(Point p : emptyPositions){
            if(occupiedPoints.contains(p)){
                return true;
            }
        }
        return false;
    }


    @Before
    public void setUp(){
        testCreature = createCreature();
        gameMap =  createDefaultSizedGameMap();
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeCreatureAtNegativeX() {
        createGameMapAndPlaceCreature(-1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeCreatureAtNegativeY() {
        createGameMapAndPlaceCreature(0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeCreatureAtTooGreatX() {
        createGameMapAndPlaceCreature(GameMap.DEFAULT_X_LENGTH, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeCreatureAtTooGreatY() {
        createGameMapAndPlaceCreature(0, GameMap.DEFAULT_Y_LENGTH);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeCreatureAtTooGreatXOnCustomSizedMap() {
        placeCreature(createCustomSizedGameMap(5, 5), 5, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeCreatureAtTooGreatYOnCustomSizedMap() {
        placeCreature(createCustomSizedGameMap(5, 5), 0, 5);
    }

    @Test
    public void getCreaturePosition() {

        Creature creature = mock(Creature.class);

        GameMap gameMap = createDefaultSizedGameMap();

        gameMap.place(creature, new Point(0, 0));

        assertEquals(new Point(0, 0), gameMap.getPosition(creature));

    }

    @Test
    public void placeSeveralCreatures() {

        Creature m1 = mock(Creature.class);
        Creature m2 = mock(Creature.class);
        Creature m3 = mock(Creature.class);

        GameMap gameMap = createDefaultSizedGameMap();

        assertTrue(gameMap.place(m1, new Point(0, 0)));
        assertTrue(gameMap.place(m2, new Point(4, 3)));
        assertTrue(gameMap.place(m3, new Point(1, 8)));

    }

    @Test
    public void placeCreatureOnAnotherCreature() {

        Creature m1 = mock(Creature.class);
        Creature m2 = mock(Creature.class);

        GameMap gameMap = createDefaultSizedGameMap();

        assertTrue(gameMap.place(m1, new Point(0, 0)));
        assertFalse(gameMap.place(m2, new Point(0, 0)));

    }

    @Test
    public void getSeveralCreaturePositions() {

        Creature m1 = mock(Creature.class);
        Creature m2 = mock(Creature.class);
        Creature m3 = mock(Creature.class);

        GameMap gameMap = createDefaultSizedGameMap();

        gameMap.place(m1, new Point(0, 0));
        gameMap.place(m2, new Point(4, 3));
        gameMap.place(m3, new Point(1, 8));

        assertEquals(new Point(0, 0), gameMap.getPosition(m1));
        assertEquals(new Point(4, 3), gameMap.getPosition(m2));
        assertEquals(new Point(1, 8), gameMap.getPosition(m3));

    }

    @Test
    public void removeCreature() {

        placeCreature(DEFAULT_SIZED_MAP, 0, 0);
        DEFAULT_SIZED_MAP.remove(mockCreature);

        assertEquals(null, DEFAULT_SIZED_MAP.getPosition(mockCreature));

    }

    @Test
    public void testPlaceGameMap() {

        GameMap lowerLevel = createDefaultSizedGameMap();
        GameMap upperLevel = createDefaultSizedGameMap();

        upperLevel.place(lowerLevel, new Point(2, 2));

        assertEquals(new Point(2, 2), upperLevel.getPosition(lowerLevel));

    }

    @Test
    public void testPlaceItem() {

        DEFAULT_SIZED_MAP.place(mockItem, GameMap.DEFAULT_ENTRY_POSITION);
        assertEquals(GameMap.DEFAULT_ENTRY_POSITION, DEFAULT_SIZED_MAP.getPosition(mockItem));

    }

    @Test
    public void testGetEntityFromPosition() {
        gameMap.place(testCreature, new Point(0,0));
        assertEquals(testCreature, gameMap.getEntity(new Point(0,0)));
    }

    @Test
    public void testGetEntityFromEmptyPosition(){
        assertEquals(null, gameMap.getEntity(new Point(0,0)));
    }

    @Test
    public void testGetAllEmptyPositionsForSpecificPlayerOnSmallEmptyMap(){
        gameMap = createCustomGameMap(3, 3);
        assertEquals(getAllEmptyPoints(3,3, new ArrayList<>()), gameMap.getAvailablePositions(mockCreature));
    }

    @Test
    public void testGetAllEmptyPositionsWhileAPlayerIsOnTheMap(){
        gameMap = createCustomGameMap(2, 2);
        gameMap.place(testCreature, new Point(1,1));
        ArrayList<Point> occupiedPoints = new ArrayList<>();
        occupiedPoints.add(new Point(1,1));

        List<Point> emptyPlaces = gameMap.getAvailablePositions(mockCreature);
        assertFalse(pointExistInList(emptyPlaces, occupiedPoints));
    }

    @Test
    public void testGetAllEmptyPositionsSeveralPlayersOnTheMap(){
        List<Point> points = placeSeveralCreaturesOnMap();
        ArrayList<Point> availablePositions = getAllEmptyPoints(10, 10, points);
        List<Point> emptyPoints = gameMap.getAvailablePositions(mockCreature);
        assertFalse(pointExistInList(emptyPoints, points));
        assertEquals(availablePositions, emptyPoints);
    }

    @Test
    public void testGetEmptyPositionsOutsideCreaturesPositionRange(){
        gameMap.place(testCreature, new Point(3,3));
        List<Point> emptyPositionsFromGameMap = gameMap.getAvailablePositions(testCreature);
        assertFalse(emptyPositionsFromGameMap.contains(new Point(7,6)));
    }

    @Test
    public void testMoveWhileFleeing(){
        gameMap.place(testCreature, new Point(3,3));
        gameMap.fleeMove(testCreature, new Point(4,4));
        assertEquals(testCreature, gameMap.getEntity(new Point(4,4)));
        assertEquals(new Point(4,4), gameMap.getPosition(testCreature));
    }

    @Test
    public void testFleeMoveToNotEmptyPoint(){
        gameMap.place(testCreature, new Point(3,3));
        assertFalse(gameMap.fleeMove(testCreature, new Point(3,3)));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testFleeToAInvalidPoint(){
        gameMap.place(testCreature, new Point(3,3));
        gameMap.fleeMove(testCreature, new Point(22,10));
    }

    @Test(expected = NullPointerException.class)
    public void testFleeWithNullCreature(){
        gameMap.fleeMove(null, new Point(3,3));
    }

    @Test(expected = NullPointerException.class)
    public void testFleeWithNullPoint(){
        gameMap.fleeMove(testCreature, null);
    }
}
