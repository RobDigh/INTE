package entity;

import entity.gameMap.GameMap;

public class Creature extends Entity {

    private int hp;
    private int speed;
    private double damageReduction;
    private double damageBonus;

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
            throw new IllegalArgumentException("Damage reduction must be greater than 0");
        }
        if(increaseValue > 100){
            throw new IllegalArgumentException("Damage reduction must be lower or equal to 100");
        }
        if((damageReduction + increaseValue) > 100){
            damageReduction = 100;
        }else{
            double newDamageReduction = Math.round(increaseValue * 100);
            damageReduction += (newDamageReduction / 100);
        }
    }

    public void decrementDamageReduction(double decreaseValue){
        damageReduction -= decreaseValue;
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

    public boolean addArmorToInventory(){
        return true;
    }

    @Override
    public boolean accept(Entity entity, GameMap environment) {
        return false;
    }
}
