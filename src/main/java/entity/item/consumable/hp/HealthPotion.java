package entity.item.consumable.hp;

import entity.Creature;
import entity.item.Item;

public class HealthPotion extends Item {

    public HealthPotion(int bonus) {
        super(bonus);
    }

    public void affect(Creature creature) {
        creature.gainHP(getBonus());
    }
}
