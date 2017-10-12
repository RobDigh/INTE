package entity.item.consumable.hp;

import entity.Creature;

public class HealthPotion {

    public HealthPotion(int bonus) {

    }

    public int getBonus() {
        return -1;
    }

    public void affect(Creature creature) {
        creature.gainHP(-1);
    }
}
