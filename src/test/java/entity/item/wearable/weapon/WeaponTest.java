package entity.item.wearable.weapon;

import entity.creature.Creature;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WeaponTest {

    private Creature mockCreature = mock(Creature.class);

    private final int LOW_DAMAGE_BONUS = 2;
    private final int HIGH_DAMAGE_BONUS = 5;
    private final int NEGATIVE_DAMAGE_BONUS = -5;

    private Weapon createWeapon() {
        return createWeapon(LOW_DAMAGE_BONUS);
    }

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

    @Test(expected = IllegalArgumentException.class)
    public void constructArmorWithNegativeDamageReduction() {
        createWeapon(NEGATIVE_DAMAGE_BONUS);
    }

    @Test
    public void affectCreature() {

        Weapon weapon = affectCreature(mockCreature);
        verify(mockCreature).incrementDamageBonus(weapon.getBonus());

    }

    @Test
    public void affectCreatureCallsCreatureMethodWithDamageBonusPassedToConstructor() {

        affectCreature(mockCreature);
        verify(mockCreature).incrementDamageBonus(LOW_DAMAGE_BONUS);

        affectCreature(HIGH_DAMAGE_BONUS, mockCreature);
        verify(mockCreature).incrementDamageBonus(HIGH_DAMAGE_BONUS);

    }

    @Test(expected = NullPointerException.class)
    public void affectNullCreature() {
        affectCreature(null);
    }

    @Test
    public void removeWeakWeaponFromCreature() {

        Weapon weapon = affectCreature(mockCreature);
        weapon.removeFrom(mockCreature);

        verify(mockCreature).decrementDamageBonus(LOW_DAMAGE_BONUS);

    }

    @Test
    public void removeStrongWeaponFromCreature() {

        Weapon weapon = affectCreature(HIGH_DAMAGE_BONUS, mockCreature);
        weapon.removeFrom(mockCreature);

        verify(mockCreature).decrementDamageBonus(HIGH_DAMAGE_BONUS);

    }

    @Test(expected = NullPointerException.class)
    public void removeFromNullCreature() {

        Weapon weapon = createWeapon();
        weapon.removeFrom(null);

    }
}
