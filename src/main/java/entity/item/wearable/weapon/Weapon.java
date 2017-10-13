package entity.item.wearable.weapon;

import entity.Creature;
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

    public void removeFrom(Creature creature) {
        creature.decrementDamageBonus(2);
    }

    @Override
    public boolean accept(Entity entity, GameMap environment) {
        return false;
    }
}
