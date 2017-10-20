package entity.item.consumable.hp;

import entity.creature.Creature;
import entity.Entity;
import entity.gameMap.GameMap;
import entity.item.Item;

public class HealthPotion extends Item {

    public HealthPotion(int bonus) {
        super(bonus);
    }

    public void affect(Creature creature) {
        creature.gainHP(getBonus());
    }
}
