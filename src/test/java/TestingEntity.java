import org.junit.Test;
import static org.junit.Assert.*;

public class TestingEntity {
    @Test
    public void testLives(){
        Entity entity = new Entity();
        assertEquals(10, entity.getLives());
    }

}
