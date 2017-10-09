import org.junit.Test;
import static org.junit.Assert.*;

public class TestingEntity {
    @Test
    public void testLives(){
        Entity entity = new Entity(10);
        assertEquals(10, entity.getLives());
    }

}
