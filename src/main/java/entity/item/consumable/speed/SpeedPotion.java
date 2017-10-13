package entity.item.consumable.speed;

import entity.Creature;

public class SpeedPotion {

    public SpeedPotion(int bonus) {

    }

    public int getBonus() {
        return -1;
    }

    public void affect(Creature creature) {
        creature.gainSpeed(-1);
    }
}
