package entity.item.wearable.weapon;

import entity.creature.Creature;
import entity.item.wearable.Wearable;

public class Weapon extends Wearable {
	
	private String type = "Weapon";

	public String getType(){
		return type;
	}
	
    public Weapon(int damageBonus) {
        super(damageBonus);
    }

    @Override
    public void affect(Creature creature) {
        creature.incrementDamageBonus(getBonus());
    }

    @Override
    public void removeFrom(Creature creature) {
        creature.decrementDamageBonus(getBonus());
    }
}
