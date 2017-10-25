package entity;

public class Monster extends Entity {

    public int getSpeed() {
        return -1;
    }

    @Override
    public boolean accept(Entity entity, GameMap environment) {
        return false;
    }
}
