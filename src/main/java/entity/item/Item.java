package entity.item;

import entity.Creature;

public abstract class Item {

    private int bonus;

    public Item(int bonus) {
        this.bonus = bonus;
    }

    protected int getBonus() {
        return bonus;
    }

    public abstract void affect(Creature creature);

}
