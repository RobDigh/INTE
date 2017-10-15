package entity.gameMap;

import org.junit.Test;

import entity.Creature;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;

public class AcceptTest extends GameMapTest {

    private GameMap lowerLevel = createDefaultSizedGameMap();
    private GameMap mockUpperLevel = mock(GameMap.class);
    private Creature player = new Creature(500, 1);
    private Creature monster = new Creature(50, 1);

    @Test
    public void acceptValidEntity() {

        assertFalse(lowerLevel.accept(mockCreature, mockUpperLevel));
        assertNotEquals(null, lowerLevel.getPosition(mockCreature));

    }

    @Test(expected = IllegalArgumentException.class)
    public void acceptNullEntity() {
        lowerLevel.accept(null, mockUpperLevel);
    }
    
    @Test
    public void acceptCreatureByMonsterAndWinBattle(){
    	assertEquals(new Point(0,0), DEFAULT_SIZED_MAP.getPosition(player));
    	assertEquals(new Point(0,1), DEFAULT_SIZED_MAP.getPosition(monster));
    }
}
