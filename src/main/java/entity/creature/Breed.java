package entity.creature;

public enum Breed {
    BULLDOG("Bulldog"),
    CHIHUAHUA("Chihuahua"),
    DACHSHUND("Dachshund"),
    DOBERMANN("Dobermann"),
    GREYHOUND("Greyhound"),
    LABRADOR("Labrador"),
    OVTJARKA("Ovtjarka"),
    SIBERIANHUSKY("Siberian husky"),
    STBERNARD("St. Bernard"),
    VIZSLA("Vizsla");

    private String name;

    Breed(String name) {
        this.name = name;
    }
}
