package entity.item.consumable.hp;

import entity.Creature;

public class HealthPotion {

    private int bonus;

    public HealthPotion(int bonus) {
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }

    public void affect(Creature creature) {
        creature.gainHP(getBonus());
    }
}
