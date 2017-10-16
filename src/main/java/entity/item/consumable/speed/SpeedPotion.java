package entity.item.consumable.speed;

import entity.creature.Creature;
import entity.Entity;
import entity.gameMap.GameMap;
import entity.item.Item;

public class SpeedPotion extends Item {

    public SpeedPotion(int bonus) {
        super(bonus);
    }

    public void affect(Creature creature) {
        creature.gainSpeed(getBonus());
    }

    @Override
    public boolean accept(Entity entity, GameMap environment) {
        return false;
    }
}
