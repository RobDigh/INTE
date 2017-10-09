import java.awt.*;
import java.util.HashMap;

public class Map {

    private int width;
    private int height;
    private HashMap<Player, Point> playerPositions = new HashMap<Player, Point>();

    public Map(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void placePlayer(Player player, Point point){
        playerPositions.put(player, point);
    }

    public Point getPosition(Player player){
        return playerPositions.get(player);
    }
}
