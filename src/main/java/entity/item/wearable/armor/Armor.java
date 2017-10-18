package entity.item.wearable.armor;

import entity.creature.Creature;
import entity.item.wearable.Wearable;

public class Armor extends Wearable {
	
	private String type = "Armor";
	
	public String getType(){
		return type;
	}
	
    public Armor(int damageReduction) {
        super(damageReduction);
    }

    @Override
    public void affect(Creature creature) {
        creature.incrementDamageReduction(getBonus());
    }

    @Override
    public void removeFrom(Creature creature) {
        creature.decrementDamageReduction(getBonus());
    }

}
