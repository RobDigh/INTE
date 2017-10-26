package entity.item.consumable.speed;

import entity.creature.Creature;
import entity.item.Item;

public class SpeedPotion extends Item {
	
	private String type = "Potion";

	public String getType(){
		return type;
	}
    public SpeedPotion(int bonus) {
        super(bonus);
    }

    public void affect(Creature creature) {
        creature.gainSpeed(getBonus());
    }
}
