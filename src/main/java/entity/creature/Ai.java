package entity.creature;

import entity.gameMap.GameMap;

import java.awt.Point;

public class Ai {

    public void flee(Creature creature, GameMap gameMap){
        gameMap.move(creature, new Point(0,1));
    }

    public void act(){

    }
}
