package player;

public class Player {

    public Player(int hp, int speed) {

        if (hp == -1) {
            throw new IllegalArgumentException("HP must be positive.");
        }

    }

    public int getHP() {
        return 100;
    }

    public int getSpeed() {
        return 1;
    }
}
