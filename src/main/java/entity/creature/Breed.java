package entity.creature;

public enum Breed {
    BULLDOG("Bulldog", 6, 5),
    CHIHUAHUA("Chihuahua", 5, 8),
    DACHSHUND("Dachshund", 5, 7),
    DOBERMANN("Dobermann", 7, 6),
    GREYHOUND("Greyhound", 5, 6),
    LABRADOR("Labrador", 6, 6),
    OVTJARKA("Ovtjarka", 8, 5),
    SIBERIANHUSKY("Siberian husky", 6, 7),
    STBERNARD("St. Bernard", 7, 5),
    VIZSLA("Vizsla", 5, 5);

    private String name;
    private int strength;
    private int dexterity;

    Breed(String name, int strength, int dexterity) {
        this.name = name;
        this.strength = strength;
        this.dexterity = dexterity;
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
}
