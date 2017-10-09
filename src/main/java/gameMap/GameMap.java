package gameMap;

import player.Player;

import java.awt.*;

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

        playerPosition = new Point(point);

        return true;

    }

    public Point getPosition(Player player) {
        return new Point(playerPosition);
    }

    private int calculateDistanceToTravelRising(int playerPosition, int speed, int edge) {

        int toSubtract = Math.max(0, (playerPosition + speed) - (edge - 1));
        return speed - toSubtract;

    }

    private int calculateDistanceToTravelFalling(int playerPosition, int speed) {

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
