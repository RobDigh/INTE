package entity.item.wearable.armor;

import entity.Creature;

public class Armor {

    private int damageReduction;

    public Armor(int damageReduction) {
        this.damageReduction = damageReduction;
    }

    public int getDamageReduction() {
        return damageReduction;
    }

    public void affect(Creature creature) {
        creature.incrementDamageReduction(getDamageReduction());
    }
}
