import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TestingEntity {
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void testLives(){
        Entity entity = new Entity(10);
        assertEquals(10, entity.getLives());
    }

    @Test
    public void testRemoveLives(){
        Entity entity = new Entity(10);
        entity.removeLife(1);
        assertEquals(9, entity.getLives());
    }

    @Test
    public void testNegativeLives(){
        Entity entity = new Entity(10);
        entity.removeLife(12);
        assertEquals(0, entity.getLives());
    }

    @Test
    public void testNegativeStartingLives(){
        thrown.expect(IllegalArgumentException.class);
        new Entity(-1);
    }

    @Test
    public void testSpeed(){
        Entity entity = new Entity(10);
        assertEquals(2, entity.getSpeed());
    }

}
