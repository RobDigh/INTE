package entity.creature;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreatureConstructorTest {

    @Test
    public void testCreateCreature() {
        Creature creature = new Creature(5, 6, 7, true);
        assertEquals(5, creature.getStrength());
        assertEquals(6, creature.getDexterity());
        assertEquals(7, creature.getConstitution());

        assertEquals(12, creature.getSpeed());
        assertEquals(70, creature.getHP());
        assertEquals(Type.GREYHOUND, creature.getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreatureWithNegativeStr(){
        new Creature(-1, 6, 7, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreatureWithNegativeDex(){
        new Creature(5, -1, 7, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreatureWithNegativeCon(){
        new Creature(5, 6, -1, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreatureWithZeroStrength(){
        new Creature(0, 6, 7, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreatureWithZeroDex(){
        new Creature(5, 0, 7, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreatureWithZeroConstitution(){
        new Creature(5, 6, 0, true);
    }

}
