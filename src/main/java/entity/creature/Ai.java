package entity.creature;

import entity.gameMap.GameMap;

import java.awt.Point;
import java.util.List;

public class Ai {

    public void flee(Creature creature, GameMap gameMap){
        //Här ska det väljas ut vilken plats som är "bäst" att flytta till. Kom även på att move-metoden tar en direction
        //och inte en point. Så måste se över hur det ska lösas.
        List<Point> availablePositions = gameMap.getAvailablePositions(creature);

    }

    public void act(){

    }
}
