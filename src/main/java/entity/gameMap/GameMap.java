package entity.gameMap;

import entity.Entity;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;

public class GameMap extends Entity {

    public static final int DEFAULT_X_LENGTH = 10;
    public static final int DEFAULT_Y_LENGTH = 10;

    public static final Point NORTH = new Point(0, 1);
    public static final Point SOUTH = new Point(0, -1);
    public static final Point WEST = new Point(1, 0);
    public static final Point EAST = new Point(-1, 0);

    public static final Point DEFAULT_ENTRY_POSITON = new Point(0, 0);

    private HashSet<Point> validDirections = new HashSet<>();

    private int xLength;
    private int yLength;

    private HashMap<Entity, Point> positionsByEntity = new HashMap<>();
    private HashMap<Point, Entity> entitiesByPosition = new HashMap<>();

    public GameMap() {
        this(DEFAULT_X_LENGTH, DEFAULT_Y_LENGTH);
    }

    public GameMap(int xLength, int yLength) {

        this.xLength = xLength;
        this.yLength = yLength;

        validDirections.add(NORTH);
        validDirections.add(SOUTH);
        validDirections.add(WEST);
        validDirections.add(EAST);

    }

    public int getXLength() {
        return xLength;
    }

    public int getYLength() {
        return yLength;
    }

    public boolean place(Entity entity, Point point) {

        if (point.x < 0 || point.y < 0) {
            throw new IllegalArgumentException("Point may only have positive coordinates.");
        }

        if (point.x >= xLength || point.y >= yLength) {
            throw new IllegalArgumentException("Point must be less than the map's size.");
        }

        return addEntity(entity, point);

    }

    private boolean addEntity(Entity entity, Point point) {

        if (positionsByEntity.get(entity) == null && entitiesByPosition.get(point) == null) {

            positionsByEntity.put(entity, point);
            entitiesByPosition.put(point, entity);

            return true;

        }
        return false;

    }

    public void remove(Entity entity) {

        Point point = positionsByEntity.get(entity);

        positionsByEntity.remove(entity);
        entitiesByPosition.remove(point);

    }

    public Point getPosition(Entity entity) {

        Point point = positionsByEntity.get(entity);

        if (point == null) {
            return null;
        }
        return new Point(point);

    }

    private boolean move(Point currentPosition, int speed, Point direction) {

        int originalX = currentPosition.x;
        int originalY = currentPosition.y;

        currentPosition.x += (direction.x * speed);
        currentPosition.y += (direction.y * speed);

        currentPosition.x = Math.max(0, currentPosition.x);
        currentPosition.x = Math.min(xLength - 1, currentPosition.x);

        currentPosition.y = Math.max(0, currentPosition.y);
        currentPosition.y = Math.min(yLength - 1, currentPosition.y);

        return ((currentPosition.x != originalX) || (currentPosition.y != originalY));

    }

    public boolean move(Entity entity, Point direction) {

        if (!validDirections.contains(direction)) {
            throw new IllegalArgumentException("Invalid direction.");
        }

        Point currentPosition = positionsByEntity.get(entity);
        entitiesByPosition.remove(currentPosition); // Do not change values that are used as hash keys.

        boolean movementSuccessful = move(currentPosition, entity.getSpeed(), direction);

        if (movementSuccessful) {

            Entity existingEntity = entitiesByPosition.get(currentPosition);
            boolean shouldStay = true;

            if (existingEntity != null) {
                shouldStay = existingEntity.accept(entity, this);
            }

            if (shouldStay) {
                // Put entity back after position has been updated.
                addEntity(entity, currentPosition); // ToDo: What happens if this fails?
            }

            return shouldStay;

        }
        return false;

    }

    @Override
    public boolean accept(Entity entity, GameMap environment) {

        if (entity == null) {
            throw new IllegalArgumentException("Entity may not be null.");
        }

        addEntity(entity, DEFAULT_ENTRY_POSITON);
        return false;

    }
}
