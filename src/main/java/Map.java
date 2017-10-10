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
        if (!positionByCreature.containsKey(creature) && creatureByPosition.get(point) != null) {
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

    public void moveCreature(Creature creature, Direction direction) {
        Point point = positionByCreature.get(creature);
        creatureByPosition.remove(point);
        int x = point.x;
        int y = point.y;
        //Can't remember where 0,0 point is, assume top left corner?
        switch (direction.getValue()) {
            case 1: //NORTH
                y -= 1;
                break;
            case 2: //NORTHEAST
                x += 1;
                y -= 1;
                break;
            case 3: //EAST
                x += 1;
                break;
            case 4: //SOUTHEAST
                x += 1;
                y += 1;
                break;
            case 5: //SOUTH
                y += 1;
                break;
            case 6: //SOUTHWEST
                x -= 1;
                y += 1;
                break;
            case 7: //WEST
                x -= 1;
                break;
            case 8: //NORTHWEST
                x -= 1;
                y -= 1;
                break;
        } //all values are 1 b/c speed indicates how many times per turn operation can be performed rather than points moved in one operation

        if(!isOutsideMap(new Point(x, y))){
        point.move(x, y);
        creatureByPosition.put(point, creature);
        }
    }

}

