package entity.item.wearable.armor;

import entity.Creature;
import entity.item.Item;

public class Armor extends Item {

    public Armor(int damageReduction) {

        super(damageReduction);

        if (damageReduction < 0) {
            throw new IllegalArgumentException("Damage reduction may not be negative.");
        }
    }

    public int getDamageReduction() {
        return getBonus();
    }

    @Override
    public void affect(Creature creature) {
        creature.incrementDamageReduction(getDamageReduction());
    }
}
