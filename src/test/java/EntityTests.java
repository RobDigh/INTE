import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class EntityTests {
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void testLives(){
        Creature creature = new Creature(10, 2);
        assertEquals(10, creature.getLives());
    }

    @Test
    public void testRemoveLives(){
        Creature creature = new Creature(10, 2);
        creature.removeLife(1);
        assertEquals(9, creature.getLives());
    }

    @Test
    public void testAddLives(){
        Creature creature = new Creature(10, 2);
        creature.addLife(7);
        assertEquals(17, creature.getLives());
    }

    @Test
    public void testNegativeLives(){
        Creature creature = new Creature(10, 2);
        creature.removeLife(12);
        assertEquals(0, creature.getLives());
    }

    @Test
    public void testNegativeStartingLives(){
        thrown.expect(IllegalArgumentException.class);
        new Creature(-1, 2);
    }

    @Test
    public void testSpeed(){
        Creature creature = new Creature(10, 2);
        assertEquals(2, creature.getSpeed());
    }

    @Test
    public void testNegativeSpeed(){
        thrown.expect(IllegalArgumentException.class);
        new Creature(10, -1);
    }

}
