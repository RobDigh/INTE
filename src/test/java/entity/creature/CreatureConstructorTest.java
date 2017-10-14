package entity.creature;

public class CreatureConstructorTest {

    public void testCreateCreature(){
        Creature creature = new Creature(5, 5, 5);
        assertEquals(5, creature.getStrength());
        assertEquals(5, creature.getDexterity());
        assertEquals(5, creature.getConstitution());
    }
}
