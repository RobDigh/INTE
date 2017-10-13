package entity;

import entity.gameMap.GameMap;
import entity.item.Item;
import entity.item.wearable.armor.Armor;
import entity.item.wearable.weapon.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Creature extends Entity {

    private int hp;
    private int speed;
    private double damageReduction;
    private double damageBonus;
    private Map<String, List> inventory = new HashMap<>();
    private List<Item> armorList = new ArrayList<>();

    public Creature(int hp, int speed) {

        if (hp <= 0) {
            throw new IllegalArgumentException("HP must be positive.");
        }

        if (speed <= 0) {
            throw new IllegalArgumentException("Speed must be positive.");
        }

        this.hp = hp;
        this.speed = speed;
        damageReduction = 0;
        damageBonus = 0;

    }

    public int getHP() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }

    public double getDamageReduction(){
        return damageReduction;
    }

    public double getDamageBonus(){
        return damageBonus;
    }

    public void gainHP(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        this.hp += amount;
    }

    public void loseHP(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        if (amount >= hp) {
            hp = 0;
            die();
            return;
        }
        this.hp -= amount;
    }

    public void die() {

    }

    public void gainSpeed(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        this.speed += amount;
    }

    public void loseSpeed(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        if (amount > speed) {
            this.speed = 0;
        } else {
            this.speed -= amount;
        }
    }

    public void incrementDamageReduction(double increaseValue) {
        if(increaseValue <= 0){
            throw new IllegalArgumentException("Increase value must be greater than 0");
        }
        if(increaseValue > 100){
            throw new IllegalArgumentException("Increase value must be less or equal to 100");
        }
        if((damageReduction + increaseValue) >= 100){
            damageReduction = 100;
        }else{
            double newDamageReduction = Math.round(increaseValue * 100);
            damageReduction += (newDamageReduction / 100);
        }
    }

    public void decrementDamageReduction(double decreaseValue){
        if(decreaseValue <= 0){
            throw new IllegalArgumentException("Decrease value must be grater than 0");
        }
        if(decreaseValue > 100){
            throw new IllegalArgumentException("Decrease value must be less or equal to 100");
        }
        if((damageReduction - decreaseValue) <= 0){
            damageReduction = 0;
        }else {
            double newDamageReduction = Math.round((damageReduction - decreaseValue) * 100);
            damageReduction = newDamageReduction / 100;
        }
    }

    public void incrementDamageBonus(double increaseValue) {
        if(increaseValue <= 0){
            throw new IllegalArgumentException("Damage bonus must be greater than 0");
        }
        if(increaseValue > 100){
            throw new IllegalArgumentException("Damage bonus must be lower or equal to 100");
        }
        if((damageBonus + increaseValue) > 100){
            damageBonus = 100;
        }else {
            double newDamageBonus = Math.round(increaseValue * 100);
            damageBonus += (newDamageBonus / 100);
        }
    }

    public void decrementDamageBonus(double decreaseValue){
        damageBonus = 15;
    }

    public boolean addArmorToInventory(Armor armor){

        armorList.add(armor);
        inventory.put("armor", armorList);
        return true;
    }

    @Override
    public boolean accept(Entity entity, GameMap environment) {
        return false;
    }
}
