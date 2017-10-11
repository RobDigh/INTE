package entity.item.wearable.armor;

import entity.Creature;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ArmorTest {

    private Creature mockCreature = mock(Creature.class);

    private Armor createArmor() {
        return new Armor();
    }

    private Armor affectCreature(Creature creature) {

        Armor armor = createArmor();
        armor.affect(creature);

        return armor;

    }


    @Test
    public void affectCreature() {

        Armor armor = affectCreature(mockCreature);
        verify(mockCreature).addArmorToInventory(armor);

    }

    @Test(expected = NullPointerException.class)
    public void affectNullCreature() {
        affectCreature(null);
    }
}
