package entity.item.wearable.armor;

import entity.Creature;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class ArmorTest {

    private Creature mockCreature = mock(Creature.class);

    private Armor createArmor() {
        return new Armor();
    }

    @Test
    public void affectCreature() {

        Armor armor = createArmor();
        armor.affect(mockCreature);

        verify(mockCreature.addArmorToInventory(armor));

    }
}
