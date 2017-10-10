import java.util.HashMap;
import java.awt.*;

public class Map {
    private int width;
    private int height;
    private HashMap<Point, Entity> entityByPosition = new HashMap<Point, Entity>();
    private HashMap<Entity, Point> positionByEntity = new HashMap<Entity, Point>();

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void placeEntity(Entity entity, Point point){
        entityByPosition.put(point, entity);
        positionByEntity.put(entity, point);
    }

    public Point getEntityPosition(Entity entity){
        return positionByEntity.get(entity);
    }
    }

