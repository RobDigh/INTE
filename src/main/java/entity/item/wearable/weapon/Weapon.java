package entity.item.wearable.weapon;

import entity.Creature;

public class Weapon {

    public Weapon(int damageBonus) {

    }

    public int getDamageBonus() {
        return -1;
    }

    public void affect(Creature creature) {
        creature.incrementDamageBonus(getDamageBonus());
    }
}
