package entity;

public abstract class Entity {

    public int getSpeed() {
        return 0;
    }

    public abstract boolean visit(Entity entity, GameMap environment);

}
