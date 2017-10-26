package entity.item;

import entity.creature.Creature;
import entity.gameMap.GameMap;
import entity.Entity;

public abstract class Item extends Entity {

    private int bonus;
    private String type;

	public String getType(){
		return type;
	}

    public Item(int bonus) {
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }

    public abstract void affect(Creature creature);
    
    @Override
    public boolean interact(Entity entity, GameMap environment){
    	
    	Creature visitingCreature = (Creature) entity;
    	Item item = this;
    	GameMap level = environment;
    	
    		visitingCreature.addItemToInventory(item, item.getType());
    		level.remove(item);
    		return true;
    }
}
