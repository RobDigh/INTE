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

    private Player player;
    private Point playerPosition;

    private HashMap<Monster, Point> positionsByMonster = new HashMap<>();
    private HashMap<Point, Monster> monstersByPosition = new HashMap<>();

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

    public boolean place(Player player, Point point) {

        if (point.x < 0 || point.y < 0) {
            throw new IllegalArgumentException("Point may only have positive coordinates.");
        }

        if (point.x >= xLength || point.y >= yLength) {
            throw new IllegalArgumentException("Point must be less than the map's size.");
        }

        if (playerPosition == null && monstersByPosition.get(point) == null) {

            this.player = player;
            playerPosition = new Point(point);

            return true;

        }

        return false;

    }

    private boolean addMonster(Monster monster, Point point) {

        if (monstersByPosition.get(point) == null && !point.equals(playerPosition)) {

            positionsByMonster.put(monster, new Point(point));
            monstersByPosition.put(new Point(point), monster);

            return true;

        }

        return false;

    }

    public void removeMonster(Monster monster) {

        Point point = positionsByMonster.get(monster);

        positionsByMonster.remove(monster);
        monstersByPosition.remove(point);

    }

    public void removePlayer(Player player) {

        this.player = null;
        playerPosition = null;

    }

    public boolean place(Monster monster, Point point) {

        if (point.x < 0 || point.y < 0) {
            throw new IllegalArgumentException("Point may only have positive coordinates.");
        }

        if (point.x >= xLength || point.y >= yLength) {
            throw new IllegalArgumentException("Point must be less than the map's size.");
        }

        return addMonster(monster, point);

    }

    public Point getPosition(Player player) {

        if (playerPosition == null) {
            return null;
        }
        return new Point(playerPosition);

    }

    public Point getPosition(Monster monster) {

        Point point = positionsByMonster.get(monster);

        if (point == null) {
            return null;
        }
        return new Point(point);

    }

    private int calculateDistanceToTravelRising(int playerPosition, int speed, int edge) {

        /*
         * playerPosition + speed gives the final position.
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
        int toSubtract = Math.max(0, (playerPosition + speed) - (edge - 1));
        return speed - toSubtract;

    }

    private int calculateDistanceToTravelFalling(int playerPosition, int speed) {

        /*
         * playerPosition - speed gives the final position.
         *
         * If the final position is less than zero, we will go past the edge, so subtract the absolute value of that
         * position from the distance to be travelled.
         *
         * Otherwise, subtract nothing.
         */
        int toSubtract = Math.abs(Math.min(0, (playerPosition - speed)));
        return speed - toSubtract;

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

    public boolean move(Player player, int direction) {

        boolean movementSuccessful = move(playerPosition, player.getSpeed(), direction);

        if (movementSuccessful) {

            Monster existingMonster = monstersByPosition.get(playerPosition);

            if (existingMonster != null) {

                return existingMonster.visit(player, this);

            }
            return true;

        }
        return false;

    }

    public boolean move(Monster monster, int direction) {

        Point monsterPosition = positionsByMonster.get(monster);
        boolean movementSuccessful = move(monsterPosition, monster.getSpeed(), direction);

        if (movementSuccessful) {

            if (monsterPosition.equals(playerPosition)) {

                return player.visit(monster, this);

            }
            return true;

        }
        return false;

    }
}
