import org.junit.Rule;
        import org.junit.Test;
        import org.junit.rules.ExpectedException;

public class MapTests {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testMapCreation() {
        Map map = new Map(30, 20);
        assertEquals(30, getWidth());
        assertEquals(20, getHeight());
    }

}
