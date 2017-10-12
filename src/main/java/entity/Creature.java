package entity;

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

    public void incrementDamageReduction(int damageReduction) {
        if(damageReduction <= 0){
            throw new IllegalArgumentException("Damage Reduction must be greater than 0");
        }
    }

    public void incrementDamageBonus(int damageBonus) {

    }

    @Override
    public boolean accept(Entity entity, GameMap environment) {
        return false;
    }
}
