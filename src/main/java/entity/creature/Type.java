package entity.creature;

public enum Type {
    BULLDOG("Bulldog", 6, 5, 7, true),
    CHIHUAHUA("Chihuahua", 5, 8, 5, true),
    DACHSHUND("Dachshund", 5, 7, 6, true),
    DOBERMANN("Dobermann", 7, 6, 5, true),
    GREYHOUND("Greyhound", 5, 6, 7, true),
    LABRADOR("Labrador", 6, 6, 6, true),
    OVTJARKA("Ovtjarka", 8, 5, 5, true),
    SIBERIANHUSKY("Siberian husky", 6, 7, 5, true),
    STBERNARD("St. Bernard", 7, 5, 6, true),
    VIZSLA("Vizsla", 5, 5, 8, true),
    BEAR("Bear", 8, 5, 5, false),
    CAR("Car", 7, 6, 5, false),
    CAT("Cat", 5, 7, 6, false),
    FOX("Fox", 6, 7, 5, false),
    HUMAN("Human", 6, 6, 6, false),
    JOGGER("Jogger", 6, 5, 7, false),
    MAILMAN("Mailman", 5, 6, 7, false),
    MOOSE("Moose", 5, 5, 8, false),
    SQUIRREL("Squirrel", 5, 8, 5, false),
    WOLF("Wolf", 7, 5, 6, false);

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
