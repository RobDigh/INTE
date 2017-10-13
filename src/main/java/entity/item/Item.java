package entity.item;

import entity.Creature;
import entity.Entity;

public abstract class Item extends Entity {

    private int bonus;

    public Item(int bonus) {
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }

    public abstract void affect(Creature creature);
}
