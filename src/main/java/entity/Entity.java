package entity;

import entity.gameMap.GameMap;

public abstract class Entity {

    public int getSpeed() {
        return 0;
    }

    /**
     * Resolves an interaction between the entity the method is invoked on and
     * the entity passed.
     *
     * @param entity The entity that visits the entity on which the method is
     *               invoked.
     * @param environment The environment the interaction takes place in.
     *                    Primarily used to remove the entity on which the
     *                    method is called if it, for example, loses combat.
     * @return true if the visiting entity should be readded to the,
     * environment, false if the visiting entity should be removed from the
     * environment.
     */
    public abstract boolean accept(Entity entity, GameMap environment);

}
