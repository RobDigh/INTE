package entity.creature;

import entity.gameMap.GameMap;

import java.awt.Point;
import java.util.List;

public class Behaviour {

    /**
     * First implementation: Flee to first empty position
     * TODO: Implement AI logic if creature is a monster. Maybe as sub classes?
     */

    public void flee(Creature creature, GameMap gameMap, boolean isPlayer) {
        List<Point> availablePositions = gameMap.getAvailablePositions(creature);

        if (!(isPlayer || availablePositions.isEmpty())) {
            gameMap.fleeMove(creature, availablePositions.get(0));
        } else {
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }

    public void act(){
        //TODO: Implement method
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
