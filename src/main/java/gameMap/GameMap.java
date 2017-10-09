package gameMap;

import player.Player;

import java.awt.*;

public class GameMap {

    public static final int DEFAULT_X_LENGTH = 10;
    public static final int DEFAULT_Y_LENGTH = 10;

    private int xLength;
    private int yLength;

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
        return true;
    }
}
