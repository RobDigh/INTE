package entity.item.wearable.armor;

import entity.creature.Creature;
import entity.item.wearable.Wearable;

public class Armor extends Wearable {

    private int damageReduction;

    public Armor(int damageReduction) {

        super(damageReduction);

        if (damageReduction < 0) {
            throw new IllegalArgumentException("Damage reduction may not be negative.");
        }
    }

    @Override
    public void affect(Creature creature) {
        creature.incrementDamageReduction(getBonus());
    }

    public void removeFrom(Creature creature) {
        creature.decrementDamageReduction(getBonus());
    }

    @Override
    public void removeFrom(Creature creature) {
        creature.decrementDamageReduction(getBonus());
    }

}
