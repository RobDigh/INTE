package entity.creature;

public enum Breed {
    BULLDOG("Bulldog", 6),
    CHIHUAHUA("Chihuahua", 5),
    DACHSHUND("Dachshund", 5),
    DOBERMANN("Dobermann", 7),
    GREYHOUND("Greyhound", 5),
    LABRADOR("Labrador", 6),
    OVTJARKA("Ovtjarka", 8),
    SIBERIANHUSKY("Siberian husky", 6),
    STBERNARD("St. Bernard", 7),
    VIZSLA("Vizsla", 5);

    private String name;
    private int strength;

    Breed(String name, int strength) {
        this.name = name;
        this.strength = strength;
    }

    public String getName(){
        return name;
    }

    public int getStrength(){
        return strength;
    }
}
