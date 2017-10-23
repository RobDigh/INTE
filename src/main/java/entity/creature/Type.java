package entity.creature;

public enum Type {
    BULLDOG("Bulldog", Creature.lowMidRangeStatValue, Creature.minInitialStatValue, Creature.highMidRangeStatValue, true),
    CHIHUAHUA("Chihuahua", Creature.minInitialStatValue, Creature.maxInitialStatValue, Creature.minInitialStatValue, true),
    DACHSHUND("Dachshund", Creature.minInitialStatValue, Creature.highMidRangeStatValue, Creature.lowMidRangeStatValue, true),
    DOBERMANN("Dobermann", Creature.highMidRangeStatValue, Creature.lowMidRangeStatValue, Creature.minInitialStatValue, true),
    GREYHOUND("Greyhound", Creature.minInitialStatValue, Creature.lowMidRangeStatValue, Creature.highMidRangeStatValue, true),
    LABRADOR("Labrador", Creature.lowMidRangeStatValue, Creature.lowMidRangeStatValue, Creature.lowMidRangeStatValue, true),
    OVTJARKA("Ovtjarka", Creature.maxInitialStatValue, Creature.minInitialStatValue, Creature.minInitialStatValue, true),
    SIBERIANHUSKY("Siberian husky", Creature.lowMidRangeStatValue, Creature.highMidRangeStatValue, Creature.minInitialStatValue, true),
    STBERNARD("St. Bernard", Creature.highMidRangeStatValue, Creature.minInitialStatValue, Creature.lowMidRangeStatValue, true),
    VIZSLA("Vizsla", Creature.minInitialStatValue, Creature.minInitialStatValue, Creature.maxInitialStatValue, true),
    BEAR("Bear", Creature.maxInitialStatValue, Creature.minInitialStatValue, Creature.minInitialStatValue, false),
    CAR("Car", Creature.highMidRangeStatValue, Creature.lowMidRangeStatValue, Creature.minInitialStatValue, false),
    CAT("Cat", Creature.minInitialStatValue, Creature.highMidRangeStatValue, Creature.lowMidRangeStatValue, false),
    FOX("Fox", Creature.lowMidRangeStatValue, Creature.highMidRangeStatValue, Creature.minInitialStatValue, false),
    HUMAN("Human", Creature.lowMidRangeStatValue, Creature.lowMidRangeStatValue, Creature.lowMidRangeStatValue, false),
    JOGGER("Jogger", Creature.lowMidRangeStatValue, Creature.minInitialStatValue, Creature.highMidRangeStatValue, false),
    MAILMAN("Mailman", Creature.minInitialStatValue, Creature.lowMidRangeStatValue, Creature.highMidRangeStatValue, false),
    MOOSE("Moose", Creature.minInitialStatValue, Creature.minInitialStatValue, Creature.maxInitialStatValue, false),
    SQUIRREL("Squirrel", Creature.minInitialStatValue, Creature.maxInitialStatValue, Creature.minInitialStatValue, false),
    WOLF("Wolf", Creature.highMidRangeStatValue, Creature.minInitialStatValue, Creature.lowMidRangeStatValue, false);

    private String name;
    private int strength;
    private int dexterity;
    private int constitution;
    private boolean isPC;

    Type(String name, int strength, int dexterity, int constitution, boolean isPC) {
        this.name = name;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.isPC = isPC;
    }

    public String getName() {
        return name;
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
}
