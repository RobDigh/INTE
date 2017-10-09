import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TestingPlayerCharacter {
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void testLives(){
        PlayerCharacter pc = new PlayerCharacter(10, 2);
        assertEquals(10, pc.getLives());
    }

    @Test
    public void testRemoveLives(){
        PlayerCharacter pc = new PlayerCharacter(10, 2);
        pc.removeLife(1);
        assertEquals(9, pc.getLives());
    }

    @Test
    public void testNegativeLives(){
        PlayerCharacter pc = new PlayerCharacter(10, 2);
        pc.removeLife(12);
        assertEquals(0, pc.getLives());
    }

    @Test
    public void testNegativeStartingLives(){
        thrown.expect(IllegalArgumentException.class);
        new PlayerCharacter(-1, 2);
    }

    @Test
    public void testSpeed(){
        PlayerCharacter pc = new PlayerCharacter(10, 2);
        assertEquals(2, pc.getSpeed());
    }

    @Test
    public void testNegativeSpeed(){
        thrown.expect(IllegalArgumentException.class);
        new PlayerCharacter(10, -1);
    }
}
