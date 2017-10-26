package entity.item.consumable.hp;

import entity.creature.Creature;
import entity.item.Item;

public class HealthPotion extends Item {
	
	private String type = "Potion";

	public String getType(){
		return type;
	}
	
    public HealthPotion(int bonus) {
        super(bonus);
    }

    public void affect(Creature creature) {
        creature.gainHP(getBonus());
    }
}
