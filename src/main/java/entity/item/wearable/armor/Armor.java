package entity.item.wearable.armor;

import entity.Creature;

public class Armor {

    public void affect(Creature creature) {
        creature.addArmorToInventory(this);
    }
}
