package entity.item.wearable.weapon;

import entity.Creature;
import entity.item.Item;

public class Weapon extends Item {

    public Weapon(int damageBonus) {

        super(damageBonus);

        if (damageBonus < 0) {
            throw new IllegalArgumentException("Damage bonus may not be negative.");
        }
    }

    public int getDamageBonus() {
        return getBonus();
    }

    @Override
    public void affect(Creature creature) {
        creature.incrementDamageBonus(getDamageBonus());
    }
}
