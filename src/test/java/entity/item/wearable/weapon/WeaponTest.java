package entity.item.wearable.weapon;

import entity.Creature;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WeaponTest {

    private Creature mockCreature = mock(Creature.class);

    private final int LOW_DAMAGE_BONUS = 2;
    private final int HIGH_DAMAGE_BONUS = 5;

    private Weapon createWeapon(int damageBonus) {
        return new Weapon(damageBonus);
    }

    private Weapon affectCreature(Creature creature) {
        return affectCreature(LOW_DAMAGE_BONUS, creature);
    }

    private Weapon affectCreature(int damageBonus, Creature creature) {

        Weapon weapon = createWeapon(damageBonus);
        weapon.affect(creature);

        return weapon;

    }

    @Test
    public void affectCreature() {

        Weapon weapon = affectCreature(mockCreature);
        verify(mockCreature).incrementDamageBonus(weapon.getDamageBonus());

    }

    @Test
    public void affectCreatureCallsCreatureMethodWithDamageReductionPassedToConstructor() {

        affectCreature(mockCreature);
        verify(mockCreature).incrementDamageBonus(LOW_DAMAGE_BONUS);

        affectCreature(HIGH_DAMAGE_BONUS, mockCreature);
        verify(mockCreature).incrementDamageBonus(HIGH_DAMAGE_BONUS);

    }
}
