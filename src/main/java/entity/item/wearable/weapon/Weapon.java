package entity.item.wearable.weapon;

import entity.Creature;

public class Weapon {

    private int damageBonus;

    public Weapon(int damageBonus) {
        this.damageBonus = damageBonus;
    }

    public int getDamageBonus() {
        return damageBonus;
    }

    public void affect(Creature creature) {
        creature.incrementDamageBonus(getDamageBonus());
    }
}
