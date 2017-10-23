package entity.gameMap;

import entity.creature.Behaviour;
import entity.creature.InventoryFactory;

import org.junit.Test;

import entity.creature.Creature;
import entity.item.wearable.armor.Armor;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;

public class AcceptTest extends GameMapTest {

    private GameMap lowerLevel = createDefaultSizedGameMap();
    private GameMap mockUpperLevel = mock(GameMap.class);

    private InventoryFactory mockInventoryFactory = mock(InventoryFactory.class);
    private Behaviour mockBehaviour = (mock(Behaviour.class));

    private Creature player = new Creature(6, 5, 7, true, mockInventoryFactory, mockBehaviour);
    private Creature monster = new Creature(7, 5, 6, false, mockInventoryFactory, mockBehaviour);
    private Armor armor = new Armor(5);
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
    	DEFAULT_SIZED_MAP.place(player, GameMap.DEFAULT_ENTRY_POSITION);
    	DEFAULT_SIZED_MAP.place(monster, new Point(0,1));
    	DEFAULT_SIZED_MAP.move(player, GameMap.NORTH);
    	
    	assertEquals(new Point(0,1), DEFAULT_SIZED_MAP.getPosition(player));
    	assertEquals(null, DEFAULT_SIZED_MAP.getPosition(monster));
    }
    
    @Test
    public void acceptCreatureByMonsterAndLoseBattle(){
    	DEFAULT_SIZED_MAP.place(monster, new Point(0,1));
    	DEFAULT_SIZED_MAP.place(player, new Point(0,0));
    	player.loseHP(451);
    	DEFAULT_SIZED_MAP.move(player, GameMap.NORTH);
    	
    	assertEquals(null, DEFAULT_SIZED_MAP.getPosition(player));
    	assertEquals(new Point(0,1), DEFAULT_SIZED_MAP.getPosition(monster));
    }
    
    @Test
    public void acceptCreatureByItemAndLeaveBoard(){
    	DEFAULT_SIZED_MAP.place(armor, GameMap.NORTH);
    	DEFAULT_SIZED_MAP.place(player, new Point(0,0));
    	DEFAULT_SIZED_MAP.move(player, GameMap.NORTH);
    	
    	assertEquals(null, DEFAULT_SIZED_MAP.getPosition(armor));
    	assertEquals(new Point(0,1), DEFAULT_SIZED_MAP.getPosition(player));
    }
}
