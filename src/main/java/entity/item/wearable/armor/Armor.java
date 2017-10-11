package entity.item.wearable.armor;

import entity.Creature;

public class Armor {

    public Armor(int damageReduction) {

    }

    public int getDamageReduction() {
        return 2;
    }

    public void affect(Creature creature) {
        creature.incrementDamageReduction(2);
    }
}
