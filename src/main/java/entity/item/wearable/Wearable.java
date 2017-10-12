package entity.item.wearable;

import entity.item.Item;

public abstract class Wearable extends Item {

    public Wearable(int bonus) {

        super(bonus);

        if (bonus < 0) {
            throw new IllegalArgumentException("Bonus may not be negative.");
        }
    }
}