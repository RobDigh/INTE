package entity;

import gameMap.GameMap;

public abstract class Entity {

    public int getSpeed() {
        return 0;
    }

    public abstract boolean visit(Entity entity, GameMap environment);

}
