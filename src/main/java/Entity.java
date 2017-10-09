
public class Entity {
    private int lives;
    private int speed;

    public Entity(int initialValueLives, int initialSpeed) {
        this.lives = initialValueLives;
        if (initialValueLives < 1) {
            throw new IllegalArgumentException("Starting lives can't be less than 1");
        }
        this.speed = initialSpeed;
        if(initialSpeed<0){
            throw new IllegalArgumentException("Speed can't be less than 0");
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

    public int getSpeed(){
        return speed;
    }
}
