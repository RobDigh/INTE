package entity.creature;

import org.junit.Test;
import static org.junit.Assert.*;

public class CreatureConstructorTest {

    @Test
    public void testCreateCreature() {
        Creature creature = new Creature(5, 6, 7);
        assertEquals(5, creature.getStrength());
        assertEquals(6, creature.getDexterity());
        assertEquals(7, creature.getConstitution());
    }
}
