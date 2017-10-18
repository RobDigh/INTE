package entity.creature;

import entity.Entity;
import entity.gameMap.GameMap;
import entity.item.Item;
import combat.Combat;


public class Creature extends Entity {

    private int hp;
    private int speed;
    private double damageReduction;
    private double damageBonus;

    private Inventory inventory;

    public Creature(int hp, int speed, int strength, int dexterity, int consitution, InventoryFactory inventoryFactory) {

        if (hp <= 0) {
            throw new IllegalArgumentException("HP must be positive.");
        }

        if (speed <= 0) {
            throw new IllegalArgumentException("Speed must be positive.");
        }

        if(strength < 5 ||strength > 8){
            throw new IllegalArgumentException("Strength must be between 5 and 8");
        }

        if(dexterity < 5 || dexterity > 8){
            throw new IllegalArgumentException("Dexterity must be between 5 and 8");
        }

        if(consitution < 5 || consitution > 8){
            throw new IllegalArgumentException("Constitution must be between 5 and 8");
        }

        this.hp = hp;
        this.speed = speed;
        damageReduction = 0;
        damageBonus = 0;

        inventory = inventoryFactory.create();
    }

    public int getHP() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }

    public double getDamageReduction(){
        return damageReduction;
    }

    public double getDamageBonus(){
        return damageBonus;
    }

    public void gainHP(int amount) {
        if(getHP() == 0){
            return;
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        this.hp += amount;
    }

    public void loseHP(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        if (amount >= hp) {
            hp = 0;
            die();
            return;
        }
        this.hp -= amount;
    }

    public void die() {

    }

    public void gainSpeed(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        this.speed += amount;
    }

    public void loseSpeed(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        if (amount > speed) {
            this.speed = 0;
        } else {
            this.speed -= amount;
        }
    }

    public void incrementDamageReduction(double increaseValue) {
        if(increaseValue <= 0){
            throw new IllegalArgumentException("Increase value must be greater than 0");
        }
        if(increaseValue > 100){
            throw new IllegalArgumentException("Increase value must be less or equal to 100");
        }
        if((damageReduction + increaseValue) >= 100){
            damageReduction = 100;
        }else{
            double newDamageReduction = Math.round(increaseValue * 100);
            damageReduction += (newDamageReduction / 100);
        }
    }

    public void decrementDamageReduction(double decreaseValue){
        if(decreaseValue <= 0){
            throw new IllegalArgumentException("Decrease value must be greater than 0");
        }
        if(decreaseValue > 100){
            throw new IllegalArgumentException("Decrease value must be less or equal to 100");
        }
        if((damageReduction - decreaseValue) <= 0){
            damageReduction = 0;
        }else {
            double newDamageReduction = Math.round((damageReduction - decreaseValue) * 100);
            damageReduction = newDamageReduction / 100;
        }
    }

    public void incrementDamageBonus(double increaseValue) {
        if(increaseValue <= 0){
            throw new IllegalArgumentException("Damage bonus must be greater than 0");
        }
        if(increaseValue > 100){
            throw new IllegalArgumentException("Damage bonus must be lower or equal to 100");
        }
        if((damageBonus + increaseValue) > 100){
            damageBonus = 100;
        }else {
            double newDamageBonus = Math.round(increaseValue * 100);
            damageBonus += (newDamageBonus / 100);
        }
    }

    public void decrementDamageBonus(double decreaseValue){
        if(decreaseValue <= 0){
            throw new IllegalArgumentException("Decrease value must be greater than 0");
        }
        if(decreaseValue >= 100){
            throw new IllegalArgumentException("Decrease value must be less or equal to 100");
        }
        if(damageBonus - decreaseValue < 0){
            damageBonus = 0;
        }else {
            double newDamageBonus = Math.round((damageBonus - decreaseValue) * 100);
            damageBonus = newDamageBonus / 100;
        }
    }

    public boolean addItemToInventory(Item item, String key){

        if(item.equals(null)){
            throw new NullPointerException("Item can't be null");
        }

        inventory.addItem(item, key);
        return true;
    }
    
	public boolean addToInventory(Item item) {
		//placeholder method for adding item to inventory
		return true;
	}

	public void act(Creature creature) {

    }

    public void flee() {

    }

    public boolean doBattle(Entity visitingEntity, Entity visitedEntity){
    	
    	Creature visitingCreature = (Creature) visitingEntity;
    	Creature visitedCreature = (Creature) visitedEntity;
    	
    	if(visitingCreature.getHP() > visitedCreature.getHP()){
    		return true;
    	}
    	if(visitingCreature.getHP() < visitedCreature.getHP()){
    		return false;
    	}
    	if(visitingCreature.getHP() == visitedCreature.getHP()){
    		//TO-DO What happens when neither Creature is killed?
    	}
    	
    	return false;
    }

    @Override
    public boolean accept(Entity entity, GameMap environment) {
    	
    	Entity visitingEntity = entity;
    	Entity visitedEntity = this;
    	
    	GameMap level = environment;
    	
    	boolean battleResultIsPositive = ((Creature) visitingEntity).doBattle(visitingEntity, visitedEntity);
    	
    	if (battleResultIsPositive == true){
    		level.remove(visitedEntity);
    		return Combat.INITIATOR_WIN;
    	}
    	
        return false;
    }

}
