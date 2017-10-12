package entity.item.wearable.armor;

import entity.Creature;
import entity.item.wearable.Wearable;

public class Armor extends Wearable {

    public Armor(int damageReduction) {
        super(damageReduction);
    }

    public int getBonus() {
        return super.getBonus();
    }

    @Override
    public void affect(Creature creature) {
        creature.incrementDamageReduction(getBonus());
    }
}
