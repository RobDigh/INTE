import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MapTester {

    @Test
    public void testCreateMap(){
        Map map = new Map(10, 20);
        assertEquals(10, map.getWidth());
        assertEquals(20, map.getHeight());
    }
}
