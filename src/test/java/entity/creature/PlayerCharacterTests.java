package entity.creature;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerCharacterTests {
    @Test
    public void testCreatePC() {
        PlayerCharacter pc = new PlayerCharacter(5, 6, 7);
        assertEquals(5, pc.getStrength());
        assertEquals(6, pc.getDexterity());
        assertEquals(7, pc.getConstitution());

        assertEquals(12, pc.getSpeed());
        assertEquals(70, pc.getHP());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPCWithNegativeStr() {
        new PlayerCharacter(-1, 6, 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPCWithNegativeDex() {
        new PlayerCharacter(5, -1, 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPCWithNegativeCon() {
        new PlayerCharacter(5, 6, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPCWithZeroStrength() {
        new PlayerCharacter(0, 6, 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPCWithZeroDex() {
        new PlayerCharacter(5, 0, 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPCWithZeroConstitution() {
        new PlayerCharacter(5, 6, 0);
    }

    /**
     * Tests for new breed-variable
     */
    @Test
    public void testBreed() {
        PlayerCharacter pc = new PlayerCharacter(5, 8, 5);
        assertEquals("Chihuahua", pc.getBreed());
    }
}
