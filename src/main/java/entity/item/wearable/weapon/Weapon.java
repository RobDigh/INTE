package entity.item.wearable.weapon;

import entity.creature.Creature;
import entity.Entity;
import entity.gameMap.GameMap;
import entity.item.wearable.Wearable;

public class Weapon extends Wearable {

    public Weapon(int damageBonus) {
        super(damageBonus);
    }

    @Override
    public void affect(Creature creature) {
        creature.incrementDamageBonus(getBonus());
    }

    @Override
    public void removeFrom(Creature creature) {
        creature.decrementDamageBonus(getBonus());
    }
}
