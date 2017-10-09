package gameMap;

import npc.Monster;
import player.Player;

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

    private Point playerPosition;
    private HashMap<Monster, Point> monsterPositions = new HashMap<>();

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

        playerPosition = new Point(point);

        return true;

    }

    public boolean place(Monster monster, Point point) {

        if (point.x < 0 || point.y < 0) {
            throw new IllegalArgumentException("Point may only have positive coordinates.");
        }

        if (point.x >= xLength || point.y >= yLength) {
            throw new IllegalArgumentException("Point must be less than the map's size.");
        }

        monsterPositions.put(monster, new Point(point));

        return true;

    }

    public Point getPosition(Player player) {
        return new Point(playerPosition);
    }

    public Point getPosition(Monster monster) {
        return new Point(monsterPositions.get(monster));
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

    public boolean move(Player player, int direction) {

        if (direction == NORTH && playerPosition.y < yLength - 1) {

            playerPosition.y += calculateDistanceToTravelRising(playerPosition.y, player.getSpeed(), yLength);

        } else if (direction == SOUTH && playerPosition.y > 0) {

            playerPosition.y -= calculateDistanceToTravelFalling(playerPosition.y, player.getSpeed());

        } else if (direction == WEST && playerPosition.x < xLength - 1) {

            playerPosition.x += calculateDistanceToTravelRising(playerPosition.x, player.getSpeed(), xLength);

        } else if (direction == EAST && playerPosition.x > 0) {

            playerPosition.x -= calculateDistanceToTravelFalling(playerPosition.x, player.getSpeed());

        } else {
            return false;
        }

        return true;

    }
}
