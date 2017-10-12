package entity.item.wearable.weapon;

import entity.Creature;
import entity.item.wearable.Wearable;

public class Weapon extends Wearable {

    public Weapon(int damageBonus) {
        super(damageBonus);
    }

    @Override
    public void affect(Creature creature) {
        creature.incrementDamageBonus(getBonus());
    }
}
