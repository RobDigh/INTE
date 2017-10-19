package entity.creature;

import entity.gameMap.GameMap;

import java.awt.Point;

public class Ai {

    public void flee(Creature creature, GameMap gameMap){

        if(gameMap.getEntity(new Point(5,7)) == null) {
            gameMap.move(creature, new Point(0, 1));
        }else{
            gameMap.move(creature, new Point(1,0));
        }
    }

    public void act(){

    }
}
