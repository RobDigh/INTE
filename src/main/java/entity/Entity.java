package entity;

import gameMap.GameMap;

public abstract class Entity {

    public abstract boolean visit(Entity entity, GameMap environment);

}
