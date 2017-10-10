import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import java.awt.*;

public class MapTests {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testMapCreation() {
        Map map = new Map(30, 20);
        assertEquals(30, map.getWidth());
        assertEquals(20, map.getHeight());
    }

    @Test
    public void testPlayerPlacement() {
        Map map = new Map(29, 19);
        PlayerCharacter pc = new PlayerCharacter(10, 2);
        Point playerStartingPoint = new Point(15, 10);
        map.placeCreature(pc, playerStartingPoint);
        assertEquals(playerStartingPoint, map.getCreaturePosition(pc));
    }

    @Test
    public void testPlacingEntityOutsideOfMap() {
        thrown.expect(IllegalArgumentException.class);
        Map map = new Map(29, 19);
        Creature creature = new Creature(10, 2);
        Point point = new Point(5, 20);
        map.placeCreature(creature, point);
    }

    @Test
    public void initialPlacementIsOccupied() {
        thrown.expect(IllegalArgumentException.class);
        Map map = new Map(29, 19);
        Creature pc = new Creature(2, 2);
        Creature monster = new Creature(2, 2);
        Point point = new Point(8, 12);
        map.placeCreature(pc, point);
        map.placeCreature(monster, point);
    }

    @Test
    public void placeAndMoveCreature(){
        Map map = new Map(29, 19);
        Creature creature = new Creature(10, 1);
        Point originalPoint = new Point(8, 5);
        Point newPoint = new Point(8, 4);
        map.placeCreature(creature, originalPoint);

        map.moveCreature(creature, Direction.NORTH);
        assertEquals(newPoint, map.getCreaturePosition(creature));
    }

    @Test
    public void testDirectionName(){
        assertEquals("North", Direction.NORTH.getName());
    }

    @Test
    public void testDirectionValues(){
        assertEquals(3, Direction.EAST.getValue());
    }

}
