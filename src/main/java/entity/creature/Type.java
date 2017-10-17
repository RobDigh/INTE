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
    VIZSLA("Vizsla", 5, 5, 8, true);

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

    public boolean isPC(){
        return isPC;
    }
}
