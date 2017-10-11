package entity;

import entity.item.wearable.armor.Armor;
import entity.item.wearable.weapon.Weapon;

public class Creature extends Entity {

    private int hp;
    private int speed;

    public Creature(int hp, int speed) {

        if (hp <= 0) {
            throw new IllegalArgumentException("HP must be positive.");
        }

        if (speed <= 0) {
            throw new IllegalArgumentException("Speed must be positive.");
        }

        this.hp = hp;
        this.speed = speed;

    }

    public int getHP() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean addArmorToInventory(Armor armor) {
        return false;
    }

    public boolean addWeaponToInventory(Weapon weapon) {
        return false;
    }

    @Override
    public boolean accept(Entity entity, GameMap environment) {
        return false;
    }
}
