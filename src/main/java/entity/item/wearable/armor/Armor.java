package entity.item.wearable.armor;

import entity.Creature;
import entity.Entity;
import entity.gameMap.GameMap;
import entity.item.wearable.Wearable;

public class Armor extends Wearable {

    public Armor(int damageReduction) {
        super(damageReduction);
    }

    @Override
    public void affect(Creature creature) {
        creature.incrementDamageReduction(getBonus());
    }

    @Override
    public void removeFrom(Creature creature) {
        creature.decrementDamageReduction(getBonus());
    }

    @Override
    public boolean accept(Entity entity, GameMap environment) {
        return false;
    }
}
