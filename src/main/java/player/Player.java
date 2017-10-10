package player;

import npc.Monster;

public class Player {

    private int hp;
    private int speed;

    public Player(int hp, int speed) {

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

    public boolean visit(Monster monster) {
        return false;
    }
}
