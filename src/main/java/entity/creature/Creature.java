package entity.creature;

import entity.Entity;
import entity.gameMap.GameMap;
import entity.item.Item;
import combat.Combat;

import java.util.*;

public class Creature extends Entity {

    public static final int minInitialStatValue = 5;
    public static final int lowMidRangeStatValue = 6;
    public static final int highMidRangeStatValue = 7;
    public static final int maxInitialStatValue = 8;
    public static final int sumOfInitialStats = 18;
    public static final int magicConstitutionHPNumber = 15;
    public static final int magicStrengthHPNumber = 5;

    private int strength;
    private int dexterity;
    private int constitution;
    private int hp;
    private int speed;
    private boolean isPC;
    private Type type;
    private double damageReduction;
    private double damageBonus;
    private Behaviour behaviour;
    private Inventory inventory;
    
    public Creature(int strength, int dexterity, int constitution,
                    boolean isPC, InventoryFactory inventoryFactory, Behaviour behaviour) {
        if (inventoryFactory == null) {
            throw new IllegalArgumentException("InventoryFactory cannot be null");
        }
        if (behaviour == null) {
            throw new IllegalArgumentException("Behaviour cannot be null");
        }

        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        checkInitialStatValues(strength, dexterity, constitution);
        this.isPC = isPC;

        this.type = calculateType();

        this.hp = calculateMaxHP();
        this.speed = calculateDefaultSpeed();
        damageReduction = 0;
        damageBonus = 0;

        inventory = inventoryFactory.create();
        this.behaviour = behaviour;
    }

    private Type calculateType() {
        Type type = null;
        ArrayList<Type> temp = new ArrayList<>(EnumSet.allOf(Type.class));
        for (int i = 0; i < temp.size(); i++) {
            Type tempType = temp.get(i);
            if (tempType.getStrength() == strength && tempType.getDexterity() == dexterity && tempType.getConstitution() == constitution && isPC == tempType.isPC()) {
                type = tempType;
            }
        }
        return type;
    }

    private void checkInitialStatValues(int strength, int dexterity, int constitution) {
        checkStatValueIsInAllowedInitialRange(strength, "Strength");
        checkStatValueIsInAllowedInitialRange(dexterity, "Dexterity");
        checkStatValueIsInAllowedInitialRange(constitution, "Constitution");

        if ((strength + dexterity + constitution) != sumOfInitialStats) {
            throw new IllegalArgumentException("The sum of strength, dexterity and constitution have to be 18");
        }
    }

    private void checkStatValueIsInAllowedInitialRange(int statValue, String statname) {
        if (statValue < minInitialStatValue || statValue > maxInitialStatValue) {
            throw new IllegalArgumentException(statname + " must be between 5 and 8");
        }
    }

    public int getHP() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }

    private int calculateMaxHP(){
        return constitution * magicConstitutionHPNumber + strength * magicStrengthHPNumber;
    }

    private int calculateDefaultSpeed() {
        if (shouldHaveMaxSpeed(dexterity, constitution)) {
            return 3;
        } else if (shouldHaveHighSpeed(dexterity, constitution)) {
            return 2;
        }
        return 1;
    }

    private boolean shouldHaveMaxSpeed(int dexterity, int constitution) {
        return (dexterity >= maxInitialStatValue && constitution >= highMidRangeStatValue);
    }

    private boolean shouldHaveHighSpeed(int dexterity, int constitution) {
        return (dexterity >= maxInitialStatValue ||
                (dexterity == highMidRangeStatValue && constitution >= lowMidRangeStatValue));
    }

    public double getDamageReduction() {
        return damageReduction;
    }

    public double getDamageBonus() {
        return damageBonus;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public boolean isPC() {
        return isPC;
    }

    public Type getType() {
        return type;
    }

    public void gainStrength(int amount){
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        this.strength += amount;
        this.hp = calculateMaxHP();
    }

    public void gainDexterity(int amount){
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        this.dexterity += amount;
        this.speed = calculateDefaultSpeed();
    }

    public void gainConstitution(int amount){
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        this.constitution += amount;
        this.speed = calculateDefaultSpeed();
        this.hp = calculateMaxHP();
    }

    public void gainHP(int amount) {
        if (getHP() == 0) {
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
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        this.speed += amount;
    }

    public void loseSpeed(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        speed = Math.max(0, speed - amount);
    }

    public void incrementDamageReduction(double increaseValue) {
        if (increaseValue <= 0) {
            throw new IllegalArgumentException("Increase value must be greater than 0");
        }
        if (increaseValue > 100) {
            throw new IllegalArgumentException("Increase value must be less or equal to 100");
        }
        if ((damageReduction + increaseValue) >= 100) {
            damageReduction = 100;
        } else {
            double newDamageReduction = Math.round(increaseValue * 100);
            damageReduction += (newDamageReduction / 100);
        }
    }

    public void decrementDamageReduction(double decreaseValue) {
        if (decreaseValue <= 0) {
            throw new IllegalArgumentException("Decrease value must be greater than 0");
        }
        if (decreaseValue > 100) {
            throw new IllegalArgumentException("Decrease value must be less or equal to 100");
        }
        if ((damageReduction - decreaseValue) <= 0) {
            damageReduction = 0;
        } else {
            double newDamageReduction = Math.round((damageReduction - decreaseValue) * 100);
            damageReduction = newDamageReduction / 100;
        }
    }

    public void incrementDamageBonus(double increaseValue) {
        if (increaseValue <= 0) {
            throw new IllegalArgumentException("Damage bonus must be greater than 0");
        }
        if (increaseValue > 100) {
            throw new IllegalArgumentException("Damage bonus must be lower or equal to 100");
        }
        if ((damageBonus + increaseValue) > 100) {
            damageBonus = 100;
        } else {
            double newDamageBonus = Math.round(increaseValue * 100);
            damageBonus += (newDamageBonus / 100);
        }
    }

    public void decrementDamageBonus(double decreaseValue) {
        if (decreaseValue <= 0) {
            throw new IllegalArgumentException("Decrease value must be greater than 0");
        }
        if (decreaseValue >= 100) {
            throw new IllegalArgumentException("Decrease value must be less or equal to 100");
        }
        if (damageBonus - decreaseValue < 0) {
            damageBonus = 0;
        } else {
            double newDamageBonus = Math.round((damageBonus - decreaseValue) * 100);
            damageBonus = newDamageBonus / 100;
        }
    }

    public boolean addItemToInventory(Item item, String key) {

        if (item.equals(null)) {
            throw new NullPointerException("Item can't be null");
        }

        inventory.addItem(item, key);
        return true;
    }

    public boolean addToInventory(Item item) {
        //placeholder method for adding item to inventory
        return true;
    }

    public boolean removeItemFromInventory(Item item, String key) {

        if (item.equals(null)) {
            throw new NullPointerException("Item can't be null");
        }

        inventory.removeItem(item, key);
        return true;
    }

    public void act(Creature creature) {
        behaviour.act();
    }

    public void flee(GameMap gameMap) {
        behaviour.flee(this, gameMap, isPC);
    }

    public boolean doBattle(Entity visitingEntity, Entity visitedEntity) {

        Creature visitingCreature = (Creature) visitingEntity;
        Creature visitedCreature = (Creature) visitedEntity;

        if (visitingCreature.getHP() > visitedCreature.getHP()) {
            return true;
        }
        if (visitingCreature.getHP() < visitedCreature.getHP()) {
            return false;
        }
        if (visitingCreature.getHP() == visitedCreature.getHP()) {
            //TO-DO What happens when neither Creature is killed?
        }

        return false;
    }

    @Override
    public boolean interact(Entity entity, GameMap environment) {

        Entity visitingEntity = entity;
        Entity visitedEntity = this;

        GameMap level = environment;

        boolean battleResultIsPositive = ((Creature) visitingEntity).doBattle(visitingEntity, visitedEntity);

        if (battleResultIsPositive == true) {
            level.remove(visitedEntity);
            return Combat.INITIATOR_WIN;
        }
        return false;
    }
}
