package gameMap;

import entity.Entity;
import entity.Monster;
import entity.Player;

import java.awt.*;
import java.util.HashMap;

public class GameMap {

    public static final int DEFAULT_X_LENGTH = 10;
    public static final int DEFAULT_Y_LENGTH = 10;

    public static final int NORTH = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    public static final int EAST = 4;

    private int xLength;
    private int yLength;

    private HashMap<Entity, Point> positionsByEntity = new HashMap<>();
    private HashMap<Point, Entity> entitiesByPosition = new HashMap<>();

    public GameMap() {

        this.xLength = DEFAULT_X_LENGTH;
        this.yLength = DEFAULT_Y_LENGTH;

    }

    public GameMap(int xLength, int yLength) {

        this.xLength = xLength;
        this.yLength = yLength;

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

    private int calculateDistanceToTravelRising(int position, int speed, int edge) {

        /*
         * position + speed gives the final position.
         *
         * Subtracting the index of the edge gives us the amount of steps past the edge that movement would have
         * resulted in.
         *
         * If the difference is negative, we will not go past the edge, so subtract zero from the distance to be
         * travelled.
         *
         * If the difference is positive, subtract the difference from the distance to be travelled so that we do not
         * go past the edge.
         */
        int distancePastEdge = (position + speed) - (edge - 1);
        int adjustmentNeeded = Math.max(0, distancePastEdge);

        return speed - adjustmentNeeded;

    }

    private int calculateDistanceToTravelFalling(int position, int speed) {

        /*
         * position - speed gives the final position.
         *
         * If the final position is less than zero, we will go past the edge, so subtract the absolute value of that
         * position from the distance to be travelled.
         *
         * Otherwise, subtract nothing.
         */
        int distancePastEdge = position - speed;
        int adjustmentNeeded = Math.abs(Math.min(0, distancePastEdge));

        return speed - adjustmentNeeded;

    }

    private boolean move(Point currentPosition, int speed, int direction) {

        if (direction == NORTH && currentPosition.y < yLength - 1) {

            currentPosition.y += calculateDistanceToTravelRising(currentPosition.y, speed, yLength);

        } else if (direction == SOUTH && currentPosition.y > 0) {

            currentPosition.y -= calculateDistanceToTravelFalling(currentPosition.y, speed);

        } else if (direction == WEST && currentPosition.x < xLength - 1) {

            currentPosition.x += calculateDistanceToTravelRising(currentPosition.x, speed, xLength);

        } else if (direction == EAST && currentPosition.x > 0) {

            currentPosition.x -= calculateDistanceToTravelFalling(currentPosition.x, speed);

        } else {
            return false;
        }

        return true;

    }

    public boolean move(Entity entity, int direction) {

        Point currentPosition = positionsByEntity.get(entity);
        entitiesByPosition.remove(currentPosition); // Do not change values that are used as hash keys.

        boolean movementSuccessful = move(currentPosition, entity.getSpeed(), direction);

        if (movementSuccessful) {

            Entity existingEntity = entitiesByPosition.get(currentPosition);
            boolean shouldStay = true;

            if (existingEntity != null) {
                shouldStay = existingEntity.visit(entity, this);
            }

            if (shouldStay) {
                // ToDo: What happens if this fails?
                addEntity(entity, currentPosition); // Put entity back after position has been updated.
            }

            return shouldStay;

        }
        return false;

    }
}
