package entity.item.wearable.weapon;

import entity.Creature;
import entity.item.Item;

public class Weapon extends Item {

    public Weapon(int damageBonus) {
        super(damageBonus);
    }

    public int getDamageBonus() {
        return getBonus();
    }

    @Override
    public void affect(Creature creature) {
        creature.incrementDamageBonus(getDamageBonus());
    }
}
