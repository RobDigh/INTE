package entity.item.consumable.speed;

import entity.Creature;
import entity.item.Item;

public class SpeedPotion extends Item {

    public SpeedPotion(int bonus) {
        super(bonus);
    }

    public void affect(Creature creature) {
        creature.gainSpeed(getBonus());
    }
}
