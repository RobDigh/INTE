package entity.item.wearable.weapon;

import entity.Creature;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WeaponTest {

    private Creature mockCreature = mock(Creature.class);

    private Weapon createWeapon() {
        return new Weapon();
    }

    private Weapon affectCreature(Creature creature) {

        Weapon weapon = createWeapon();
        weapon.affect(creature);

        return weapon;

    }

    @Test
    public void affectCreature() {

        Weapon weapon = affectCreature(mockCreature);

    }
}
