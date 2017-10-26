package entity.item.consumable.hp;

import entity.creature.Creature;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HealthPotionTest {

    private Creature mockCreature = mock(Creature.class);

    private final int LOW_HEALTH_BONUS = 2;
    private final int HIGH_HEALTH_BONUS = 5;
    private final int NEGATIVE_HEALTH_BONUS = -5;

    private HealthPotion createHealthPotion(int bonus) {
        return new HealthPotion(bonus);
    }

    private HealthPotion affectCreature(Creature creature) {
        return affectCreature(LOW_HEALTH_BONUS, creature);
    }

    private HealthPotion affectCreature(int bonus, Creature creature) {

        HealthPotion healthPot = createHealthPotion(bonus);
        healthPot.affect(creature);

        return healthPot;

    }

    @Test
    public void testGetType(){
        HealthPotion healthPotion = createHealthPotion(2);
        assertEquals("Potion", healthPotion.getType());
    }

    @Test
    public void constructFakeHealthPotion() {

        HealthPotion healthPot = createHealthPotion(NEGATIVE_HEALTH_BONUS);
        assertEquals(NEGATIVE_HEALTH_BONUS, healthPot.getBonus());

    }

    @Test
    public void affectCreature() {

        HealthPotion healthPot = affectCreature(mockCreature);
        verify(mockCreature).gainHP(healthPot.getBonus());

    }

    @Test
    public void affectCreatureCallsCreatureMethodWithBonusPassedToConstructor() {

        affectCreature(mockCreature);
        verify(mockCreature).gainHP(LOW_HEALTH_BONUS);

        affectCreature(HIGH_HEALTH_BONUS, mockCreature);
        verify(mockCreature).gainHP(HIGH_HEALTH_BONUS);

    }

    @Test(expected = NullPointerException.class)
    public void affectNullCreature() {
        affectCreature(null);
    }
}
