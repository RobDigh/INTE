package entity.item;

import entity.Creature;
import entity.Entity;
import entity.gameMap.GameMap;

public abstract class Item extends Entity {

    private int bonus;

    public Item(int bonus) {
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }

    public abstract void affect(Creature creature);
    
    @Override
    public boolean accept(Entity entity, GameMap environment){
    	
    	Creature visitingCreature = (Creature) entity;
    	Item item = this;
    	GameMap level = environment;
    	
    	if(item instanceof Item){
    		visitingCreature.addItemToInventory(item);
    		level.remove(item);
    		return true;
    	}
    	
    	return false;
    }
    
}
