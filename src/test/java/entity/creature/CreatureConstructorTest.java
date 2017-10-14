package entity.creature;

public class CreatureConstructorTest {

    public void testCreateCreature(){
        Creature creature = new Creature(5, 6, 7);
        assertEquals(5, creature.getStrength());
        assertEquals(6, creature.getDexterity());
        assertEquals(7, creature.getConstitution());
    }
}
