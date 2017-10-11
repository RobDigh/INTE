package entity.item.wearable.armor;

import entity.Creature;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ArmorTest {

    private Creature mockCreature = mock(Creature.class);
    private int LOW_DAMAGE_REDUCTION = 2;

    private Armor createArmor() {
        return new Armor(LOW_DAMAGE_REDUCTION);
    }

    private Armor affectCreature(Creature creature) {

        Armor armor = createArmor();
        armor.affect(creature);

        return armor;

    }

    @Test
    public void affectCreature() {

        Armor armor = affectCreature(mockCreature);
        verify(mockCreature).incrementDamageReduction(armor.getDamageReduction());

    }

    @Test
    public void affectCreatureCallsCreatureMethodWithDamageReductionPassedToconstructor() {

        Armor armor = affectCreature(mockCreature);
        verify(mockCreature).incrementDamageReduction(LOW_DAMAGE_REDUCTION);

    }

    @Test(expected = NullPointerException.class)
    public void affectNullCreature() {
        affectCreature(null);
    }
}
