import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MainTest {

    @Test
    public void testTrue(){
        assertTrue(true);
    }

    @Test
    public void testMockMain(){
        Main main = mock(Main.class);
        when(main.maja()).thenReturn("Robert");
        assertEquals("Robert", main.maja());
    }


}