package entity.creature;

import entity.gameMap.GameMap;

import java.awt.Point;
import java.util.List;

public class Behaviour {

    /**
     * First implementation: Flee to first empty position
     * TODO: Implement AI logic if creature is a monster. And handle player instructions.
     */

    public void flee(Creature creature, GameMap gameMap, boolean isPlayer) {
        List<Point> availablePositions = gameMap.getAvailablePositions(creature);

        if(isPlayer){
            throw new UnsupportedOperationException("Not implemented yet");
        }else if(!availablePositions.isEmpty()){
            gameMap.fleeMove(creature, availablePositions.get(0));
        }else {
            //TODO: implement what should happen if no empty points are available
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }

    public void act(){
        //TODO: Implement method
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
