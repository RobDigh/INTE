package entity.creature;

public enum Breed {
    BULLDOG("Bulldog", 6, 5, 7),
    CHIHUAHUA("Chihuahua", 5, 8, 5),
    DACHSHUND("Dachshund", 5, 7, 6),
    DOBERMANN("Dobermann", 7, 6, 5),
    GREYHOUND("Greyhound", 5, 6, 7),
    LABRADOR("Labrador", 6, 6, 6),
    OVTJARKA("Ovtjarka", 8, 5, 5),
    SIBERIANHUSKY("Siberian husky", 6, 7, 5),
    STBERNARD("St. Bernard", 7, 5, 6),
    VIZSLA("Vizsla", 5, 5, 8);

    private String name;
    private int strength;
    private int dexterity;
    private int constitution;

    Breed(String name, int strength, int dexterity, int constitution) {
        this.name = name;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
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
}
