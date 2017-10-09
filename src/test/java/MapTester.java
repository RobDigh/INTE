import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MapTester {

    Map map = new Map(10, 20);

    @Test
    public void testCreateMap(){
        assertEquals(10, map.getWidth());
        assertEquals(20, map.getHeight());
    }

    @Test
    public void testPlacePlayer(){
        Player player = new Player("Maja");
        map.placePlayer(player);

    }
}
