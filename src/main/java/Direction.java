public enum Direction {
    NORTH("North", 1),
    NORTHEAST("Northeast", 2),
    EAST("East", 3),
    SOUTHEAST("Southeast", 4),
    SOUTH("South", 5),
    SOUTHWEST("Southwest", 6),
    WEST("West", 7),
    NORTHWEST("Northwest", 8);
    private String name;
    private int value;

    Direction(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
