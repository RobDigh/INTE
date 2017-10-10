package entity;

import gameMap.GameMap;

public class Monster extends Entity {

    public int getSpeed() {
        return -1;
    }

    @Override
    public boolean visit(Entity entity, GameMap environment) {
        return false;
    }
}
