
public class Entity {
    private int lives;

    public Entity(int initialValueLives) {
        this.lives = initialValueLives;
    }

    public int getLives() {
        return lives;
    }

    public void removeLife(int nrOfLivesToRemove) {
        this.lives = lives - nrOfLivesToRemove;
    }
}
