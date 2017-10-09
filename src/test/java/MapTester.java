import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertEquals;

public class MapTester {

    Map map;
    Player player;

    @Before
    public void setUp() {
        map = new Map(10, 20);
        player = new Player("Maja");
        map.placePlayer(player, new Point(10, 10));
    }

    @Test
    public void testCreateMap(){
        assertEquals(10, map.getWidth());
        assertEquals(20, map.getHeight());
    }

    @Test
    public void testPlacePlayer(){
        assertEquals(new Point(10,10), map.getPosition(player));
    }

    @Test
    public void testMovePlayer(){
        map.placePlayer(player, new Point(20, 30));
    }
}
