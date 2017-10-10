import java.util.HashMap;
import java.awt.*;

public class Map {
    private int width;
    private int height;
    private HashMap<Point, Creature> creatureByPosition = new HashMap<Point, Creature>();
    private HashMap<Creature, Point> positionByCreature = new HashMap<Creature, Point>();

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

    public void placeCreature(Creature creature, Point point) {
        if(!positionByCreature.containsKey(creature) && creatureByPosition.get(point) != null){
            throw new IllegalArgumentException("Creature cannot have initial placement in occupied space");
        }
        if (isOutsideMap(point)) {
            throw new IllegalArgumentException("Position can't be outside of map");
        } else {
            creatureByPosition.put(point, creature);
            positionByCreature.put(creature, point);
        }
    }

    private boolean isOutsideMap(Point point) {
        return point.getX() < 0 || point.getY() < 0 || point.getX() > getWidth() || point.getY() > getHeight();
    }

    public Point getCreaturePosition(Creature creature) {
        return positionByCreature.get(creature);
    }

    public void moveCreature(Creature creature, int speed, int direction){
        Point position = positionByCreature.get(creature);

    }
}

