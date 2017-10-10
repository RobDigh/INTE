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

    public void placeEntity(Entity entity, Point point) {
        if (isOutsideMap(point)) {
            throw new IllegalArgumentException("Position can't be outside of map");
        } else {
            entityByPosition.put(point, entity);
            positionByEntity.put(entity, point);
        }
    }

    private boolean isOutsideMap(Point point) {
        if (point.getX() < 0 || point.getY() < 0 || point.getX() > getWidth() || point.getY() > getHeight()) {
            return true;
        }
        return false;
    }

    public Point getEntityPosition(Entity entity) {
        return positionByEntity.get(entity);
    }
}

