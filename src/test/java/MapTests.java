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
    public void testPlayerPlacement(){
        Map map = new Map(29, 19);
        PlayerCharacter pc = new PlayerCharacter();
        Point playerStartingPoint = new Point(15, 10);
        map.placePlayer(pc, playerStartingPoint);
        assertEquals(playerStartingPoint, map.getPlayerPosition);
    }

}
