import org.junit.Test;
import static org.junit.Assert.*;

public class TestingEntity {
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

}
