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
        this.hp -= amount;
    }

    public void gainSpeed(int amount) {
        this.speed += amount;
    }

    public void incrementDamageReduction(int damageReduction) {

    }

    public void incrementDamageBonus(int damageBonus) {

    }

    @Override
    public boolean accept(Entity entity, GameMap environment) {
        return false;
    }
}
