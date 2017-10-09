
public class Entity {
    private int lives;
    private int speed;

    public Entity(int initialValueLives, int initialSpeed) {
        this.lives = initialValueLives;
        if (initialValueLives < 1) {
            throw new IllegalArgumentException("starting lives can't be less than 1");
        }
        this.speed = initialSpeed;
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

    public int getSpeed(){
        return speed;
    }
}
