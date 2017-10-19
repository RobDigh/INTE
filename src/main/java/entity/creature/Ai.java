package entity.creature;

import entity.gameMap.GameMap;

import java.awt.Point;
import java.util.List;

public class Ai {

    public void flee(Creature creature, GameMap gameMap){
        List<Point> avaiblablePositions = gameMap.getAvailablePositions(creature);
    }

    public void act(){

    }
}
