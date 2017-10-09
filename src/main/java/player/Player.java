package player;

public class Player {

    private int hp;

    public Player(int hp, int speed) {

        if (hp <= 0) {
            throw new IllegalArgumentException("HP must be positive.");
        }

        if (speed <= 0) {
            throw new IllegalArgumentException("Speed must be positive.");
        }

        this.hp = hp;

    }

    public int getHP() {
        return hp;
    }

    public int getSpeed() {
        return 1;
    }
}
