package entity.item.wearable;

import entity.Creature;

public abstract class Wearable {

    private int bonus;

    public Wearable(int bonus) {
        this.bonus = bonus;
    }

    protected int getBonus() {
        return bonus;
    }

    public abstract void affect(Creature creature);

}
