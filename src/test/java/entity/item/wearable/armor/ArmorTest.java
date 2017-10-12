package entity.item.wearable.armor;

import entity.Creature;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ArmorTest {

    private Creature mockCreature = mock(Creature.class);

    private final int LOW_DAMAGE_REDUCTION = 2;
    private final int HIGH_DAMAGE_REDUCTION = 5;
    private final int NEGATIVE_DAMAGE_REDUCTION = 5;

    private Armor createArmor(int damageReduction) {
        return new Armor(damageReduction);
    }

    private Armor createArmor() {
        return createArmor(LOW_DAMAGE_REDUCTION);
    }

    private Armor affectCreature(Creature creature) {
        return affectCreature(LOW_DAMAGE_REDUCTION, creature);
    }

    private Armor affectCreature(int damageReduction, Creature creature) {

        Armor armor = createArmor(damageReduction);
        armor.affect(creature);

        return armor;

    }

    @Test(expected = IllegalArgumentException.class)
    public void constructArmorWithNegativeDamageReduction() {
        createArmor(NEGATIVE_DAMAGE_REDUCTION);
    }

    @Test
    public void affectCreature() {

        Armor armor = affectCreature(mockCreature);
        verify(mockCreature).incrementDamageReduction(armor.getDamageReduction());

    }

    @Test
    public void affectCreatureCallsCreatureMethodWithDamageReductionPassedToConstructor() {

        affectCreature(mockCreature);
        verify(mockCreature).incrementDamageReduction(LOW_DAMAGE_REDUCTION);

        affectCreature(HIGH_DAMAGE_REDUCTION, mockCreature);
        verify(mockCreature).incrementDamageReduction(HIGH_DAMAGE_REDUCTION);

    }

    @Test(expected = NullPointerException.class)
    public void affectNullCreature() {
        affectCreature(null);
    }
}
