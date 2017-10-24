package entity.creature;

import entity.gameMap.GameMap;

import java.awt.Point;
import java.util.List;

public class Behaviour {

    /**
     * First implementation: Flee in first valid direction found
     * TODO: Implement AI logic if creature is a monster and handle player actions. Maybe as sub classes?
     */

    public void flee(Creature creature, GameMap gameMap, boolean isPlayer) {

        if(gameMap == null){
            throw new IllegalArgumentException("Game map can't be null");
        }
        
        List<Point> availableDirections = gameMap.getAvailableDirections(creature);

        if(isPlayer){
            throw new UnsupportedOperationException("Not implemented yet");
        }else if(!availableDirections.isEmpty()){
            gameMap.move(creature, availableDirections.get(0));
        }else {
            //TODO: implement what should happen if no empty points are available
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }

    public void act(Creature creature){
        //TODO: Implement method
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
