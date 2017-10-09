import java.awt.*;

public class Map {

    private int width;
    private int height;

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

    }

    public Point getPosition(Player player){
        return new Point(10, 10);
    }
}
