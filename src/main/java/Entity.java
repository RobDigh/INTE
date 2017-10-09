
public class Entity {
    private int lives;

    public Entity(int initialValueLives) {
        this.lives = initialValueLives;
        if (initialValueLives < 1) {
            throw new IllegalArgumentException("starting lives can't be less than 1");
        }
    }

    public int getLives() {
        return lives;
    }

    public void removeLife(int nrOfLivesToRemove) {
        this.lives = lives - nrOfLivesToRemove;

        if (lives < 0) {
            lives = 0;
        }
    }
}
