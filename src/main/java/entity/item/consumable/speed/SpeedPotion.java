package entity.item.consumable.speed;

import entity.Creature;

public class SpeedPotion {

    private int bonus;

    public SpeedPotion(int bonus) {
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }

    public void affect(Creature creature) {
        creature.gainSpeed(getBonus());
    }
}
