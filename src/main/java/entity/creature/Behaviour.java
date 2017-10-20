package entity.creature;

import entity.gameMap.GameMap;

import java.awt.Point;
import java.util.List;

public class Behaviour {

    /**
     * First implementation: Flee to first empty position
     * TODO: Implement AI logic if creature is a monster
     * TODO: Implement act method
     */

    public void flee(Creature creature, GameMap gameMap, boolean isPlayer) {
        List<Point> availablePositions = gameMap.getAvailablePositions(creature);

        if (isPlayer) {
            throw new UnsupportedOperationException("Not implemented yet");
        } else {
            gameMap.fleeMove(creature, new Point(2, 2));
        }
    }

    public void act(){
        //TODO: Implement method
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
