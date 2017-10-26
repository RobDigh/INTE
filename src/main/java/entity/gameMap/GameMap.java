package entity.gameMap;

import entity.Entity;
import entity.creature.Creature;
import entity.item.Item;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GameMap extends Entity {

    public static final int DEFAULT_X_LENGTH = 10;
    public static final int DEFAULT_Y_LENGTH = 10;

    public static final Point NORTH = new Point(0, 1);
    public static final Point SOUTH = new Point(0, -1);
    public static final Point WEST = new Point(-1, 0);
    public static final Point EAST = new Point(1, 0);

    public static final Point DEFAULT_ENTRY_POSITION = new Point(0, 0);

    private static final HashSet<Point> validDirections = new HashSet<>();

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

    private void makeNullCheck(Object object, String name) {
        if (object == null) {
            throw new IllegalArgumentException(name + " may not be null.");
        }
    }

    private void checkForNullEntity(Entity entity) {
        makeNullCheck(entity, "Entity");
    }

    private void checkForNullPoint(Point point) {
        makeNullCheck(point, "Point");
    }

    public boolean placeEntity(Entity entity, Point point) {

        checkForNullEntity(entity);
        checkForNullPoint(point);

        if (point.x < 0 || point.y < 0) {
            throw new IllegalArgumentException("Point may only have positive coordinates.");
        }

        if (point.x >= xLength || point.y >= yLength) {
            throw new IllegalArgumentException("Point must be less than the map's size.");
        }


        if (positionsByEntity.get(entity) == null && entitiesByPosition.get(point) == null) {
            addEntity(entity, point);
            return true;
        }
        return false;

    }

    private void addEntity(Entity entity, Point point) {

        checkForNullEntity(entity);
        checkForNullPoint(point);

        positionsByEntity.put(entity, point);
        entitiesByPosition.put(point, entity);

    }

    public boolean remove(Entity entity) {

        checkForNullEntity(entity);

        Point point = positionsByEntity.remove(entity);
        entitiesByPosition.remove(point);

        return point != null;
    }

    public Point getPosition(Entity entity) {

        checkForNullEntity(entity);

        Point point = positionsByEntity.get(entity);

        if (point == null) {
            return null;
        }
        return new Point(point);

    }

    public Entity getEntity(Point point){

        checkForNullPoint(point);
        return entitiesByPosition.get(point);

    }

    private boolean updatePosition(Point currentPosition, int speed, Point direction) {

        checkForNullPoint(currentPosition);
        checkForNullPoint(direction);

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

    private void resolveShouldStay(Entity entity, Point currentPosition, boolean shouldStay) {

        if (shouldStay) {
            // Put entity back after position has been updated.
            addEntity(entity, currentPosition); // ToDo: What happens if this fails?
        } else {
            remove(entity);
        }
    }

    public boolean move(Creature creature, Point direction) {

        makeNullCheck(creature, "Creature");

        if (!validDirections.contains(direction)) {
            throw new IllegalArgumentException("Invalid direction.");
        }

        Point currentPosition = positionsByEntity.get(creature);
        entitiesByPosition.remove(currentPosition); // Do not change values that are used as hash keys.

        boolean movementSuccessful = updatePosition(currentPosition, creature.getSpeed(), direction);

        if (movementSuccessful) {

            Entity existingEntity = entitiesByPosition.get(currentPosition);
            boolean shouldStay = true;

            if (existingEntity != null) {
                shouldStay = existingEntity.interact(creature, this);
            }

            resolveShouldStay(creature, currentPosition, shouldStay);

            return shouldStay;

        }
        return false;

    }

    @Override
    public boolean interact(Entity entity, GameMap environment) {

        if (entity == null) {
            throw new IllegalArgumentException("Entity may not be null.");
        }

        addEntity(entity, DEFAULT_ENTRY_POSITION);
        return false;
    }

    public ArrayList<Point> getAvailableDirections(Creature creature){
        ArrayList<Point> availableDirections = new ArrayList<Point>();
        Point currentPosition = getPosition(creature);

        for(Point p : validDirections){

            int positionToCheckX = currentPosition.x + (p.x * creature.getSpeed());
            int positionToCheckY = currentPosition.y + (p.y * creature.getSpeed());

            Entity entityOnPosition = getEntity(new Point(positionToCheckX, positionToCheckY));
            if(entityOnPosition == null || entityOnPosition instanceof Item){
                availableDirections.add(p);
            }
        }
        return availableDirections;
    }
}
